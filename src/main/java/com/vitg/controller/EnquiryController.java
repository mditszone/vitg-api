package com.vitg.controller;


import com.vitg.dto.EmailDTO;
import com.vitg.dto.EnquiresDTO;
import com.vitg.dto.Mail;
import com.vitg.dto.ResponseDTO;
import com.vitg.service.EnquiresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/enquiry")
public class EnquiryController {

    @Autowired
    private EnquiresService enquiresService;

    @PostMapping("/createEnquiry")
    public ResponseEntity<ResponseDTO> createEnquiry(@RequestBody EnquiresDTO enquiresDTO) {
        try {
            ResponseDTO responseDTO = enquiresService.createEnquiry(enquiresDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }

    @GetMapping("/getAllEnquires")
    public ResponseEntity<List<EnquiresDTO>> getAllEnquires() {
        try {
            return new ResponseEntity<>(enquiresService.getAllEnquires(), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }

    @GetMapping("/getAllEnquiryById/{id}")
    public ResponseEntity<EnquiresDTO> getAllEnquiryById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(enquiresService.getEnquiryById(id), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }

    @PostMapping("/sendMail")
    public ResponseEntity<ResponseDTO> sendEmail(@RequestBody Mail mail) {
        try {
            ResponseDTO responseDTO = enquiresService.sendEmail(mail.getSubject(), mail.getTo(), mail.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getStatus());
        }
    }

}
