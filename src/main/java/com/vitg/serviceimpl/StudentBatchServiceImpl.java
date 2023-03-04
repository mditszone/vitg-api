package com.vitg.serviceimpl;

import com.vitg.config.CustomPropertyConfig;
import com.vitg.dto.*;
import com.vitg.entity.StudentBatch;
import com.vitg.entity.Trainer;
import com.vitg.entity.TrainerCourse;
import com.vitg.repository.BatchRepository;
import com.vitg.repository.StudentBatchRepository;
import com.vitg.repository.TrainerRepository;
import com.vitg.service.AwsSesService;
import com.vitg.service.EmailService;
import com.vitg.service.StudentBatchService;
import com.vitg.service.TrainerCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentBatchServiceImpl implements StudentBatchService {

    @Autowired
    private StudentBatchRepository studentBatchRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private TrainerCourseService trainerCourseService;


    @Autowired
    private AwsSesService awsSesService;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomPropertyConfig customPropertyConfig;

    @Override
    public List<String> getStudentsPhoneNumberByBatchId(int batchId) {
        return studentBatchRepository.findAll()
                .stream()
                .filter(item -> item.getBatch().getId() == batchId)
                .map(item -> item.getStudent().getPhoneNumber())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getStudentsEmailsByBatchId(int batchId) {
        return studentBatchRepository.findAll()
                .stream()
                .filter(item -> item.getBatch().getId() == batchId)
                .map(item -> item.getStudent().getEmail())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isMessageSentPreviously(int batchId) {
        StudentBatch studentBatch = studentBatchRepository.findByBatchId(batchId);
        return studentBatch.isMessageSent();
    }


    @Override
    public ResponseDTO sendEmailToRegisterStudentsAndTrainer(EmailDTO emailDTO) {

        List<String> phoneNumberToSendSMS = new ArrayList<>();
        List<String> failedEmailsList = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setFailedEmailList(new ArrayList<>());

        if (!emailDTO.getEmails().isEmpty()) {
            phoneNumberToSendSMS.addAll(emailDTO.getEmails());
        }

        if (emailDTO.isSendStudents()) {
            List<String> studentPhoneNumbers = getStudentsEmailsByBatchId(emailDTO.getBatchId());
            System.out.print("students: ");
            System.out.println(studentPhoneNumbers);
            phoneNumberToSendSMS.addAll(studentPhoneNumbers);
        }

        if (emailDTO.isSendTrainer()) {
            String trainerPhoneNumber = trainerRepository.findById(emailDTO.getTrainerId()).getEmail();
            System.out.print("trainers: ");
            System.out.println(trainerPhoneNumber);
            phoneNumberToSendSMS.add(trainerPhoneNumber);
        }

        try {
            Map<String, Object> model = new HashMap<>();
            model.put("url", emailDTO.getUrl());
            model.put("message", emailDTO.getMessage());
            for (String email: phoneNumberToSendSMS) {
                System.out.print("email: ");
                System.out.print(email);
                System.out.print(", message: ");
                System.out.println(emailDTO.getMessage());
                Mail mail = new Mail();
                mail.setFrom(customPropertyConfig.mailFrom);
                mail.setSubject(emailDTO.getSubject());
                mail.setTo(email);
                mail.setModel(model);
                boolean isSend = emailService.sendEmail(mail);
                if(!isSend) responseDTO.getFailedEmailList().add(email);
            }
            if (responseDTO.getFailedEmailList().size() > 0) {
                responseDTO.setMessage("email invalid");
                responseDTO.setStatus(400);
                return responseDTO;
            }
            responseDTO.setMessage("Success fully sent Email");
            responseDTO.setStatus(200);
            return responseDTO;
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return responseDTO;
        }
    }

    @Override
    public ResponseDTO sendSMStoRegisterStudentsAndTrainer(SmsDTO smsDTO) {
        List<String> emails = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        System.out.println(smsDTO);
        if (!smsDTO.getEmails().isEmpty()) {
            emails.addAll(smsDTO.getEmails());
        }

        if (smsDTO.isSendStudents()) {
            List<String> studentPhoneNumbers = getStudentsPhoneNumberByBatchId(smsDTO.getBatchId());
            System.out.print("students: ");
            System.out.println(studentPhoneNumbers);
            emails.addAll(studentPhoneNumbers);
        }

        if (smsDTO.isSendTrainer()) {
            String trainerPhoneNumber = trainerRepository.findById(smsDTO.getTrainerId()).getPhoneNumber();
            System.out.print("trainers: ");
            System.out.println(trainerPhoneNumber);
            emails.add(trainerPhoneNumber);
        }

        try {
            for (String phoneNumber: emails) {
                System.out.print("phone number: ");
                System.out.print(phoneNumber);
                System.out.print(", message: ");
                System.out.println(smsDTO.getMessage());
                awsSesService.sendSMSMessage(smsDTO.getMessage(), phoneNumber);
            }
            responseDTO.setMessage("Success fully sent SMS");
            return responseDTO;
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            return responseDTO;
        }
    }
}
