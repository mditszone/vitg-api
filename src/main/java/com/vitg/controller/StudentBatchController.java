package com.vitg.controller;

import com.vitg.config.CustomPropertyConfig;
import com.vitg.dto.*;
import com.vitg.service.EmailService;
import com.vitg.service.StudentBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/studentBatch")
public class StudentBatchController {

    @Autowired
    private StudentBatchService studentBatchService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomPropertyConfig customPropertyConfig;



    @PostMapping("/sendSMSToBatch")
    public ResponseEntity<ResponseDTO> sendSMStoBatch(@RequestBody SmsDTO smsDTO) {
        try {
            ResponseDTO responseDTO = studentBatchService.sendSMStoRegisterStudentsAndTrainer(smsDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }


    @PostMapping("/sendEmailToBatch")
    public ResponseEntity<ResponseDTO> sendEmailToBatch(@RequestBody EmailDTO emailDTO) {
        try {
            ResponseDTO responseDTO = studentBatchService.sendEmailToRegisterStudentsAndTrainer(emailDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }
}
