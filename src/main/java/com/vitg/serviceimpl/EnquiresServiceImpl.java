package com.vitg.serviceimpl;

import com.vitg.config.CustomPropertyConfig;
import com.vitg.dto.Mail;
import com.vitg.service.EmailService;
import org.modelmapper.ModelMapper;
import com.vitg.dto.EnquiresDTO;
import com.vitg.dto.ResponseDTO;
import com.vitg.entity.Enquires;
import com.vitg.repository.EnquiresRepository;
import com.vitg.service.EnquiresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EnquiresServiceImpl implements EnquiresService {

    @Autowired
    private EnquiresRepository enquiresRepository;

    @Autowired
    private EmailService emailService;


    @Autowired
    private CustomPropertyConfig customPropertyConfig;

    @Override
    public ResponseDTO createEnquiry(EnquiresDTO enquiresDTO) {
        ModelMapper modelMapper = new ModelMapper();
        enquiresDTO.setStatus("New");
        Enquires enquires = modelMapper.map(enquiresDTO, Enquires.class);
        enquiresRepository.save(enquires);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("enquiry created successfully");
        return responseDTO;
    }

    @Override
    public List<EnquiresDTO> getAllEnquires() {
        ModelMapper modelMapper = new ModelMapper();
        return enquiresRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, EnquiresDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EnquiresDTO getEnquiryById(int id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(enquiresRepository.findById(id), EnquiresDTO.class);
    }

    @Override
    public ResponseDTO sendEmail(String subject, String to, String message) {
        ResponseDTO responseDTO = new ResponseDTO();
        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setTo(to);
        mail.setFrom(customPropertyConfig.mailFrom);
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        mail.setModel(model);
        System.out.println("test");
        try {
            boolean isSend = emailService.sendEmail(mail);
            if(!isSend) responseDTO.getFailedEmailList().add(to);
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
}
