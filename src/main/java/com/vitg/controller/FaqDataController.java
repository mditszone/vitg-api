package com.vitg.controller;


import com.vitg.dto.EnquiresDTO;
import com.vitg.dto.FaqDataDTO;
import com.vitg.dto.ResponseDTO;
import com.vitg.service.FaqDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
public class FaqDataController {

    @Autowired
    private FaqDataService faqDataService;

    @PostMapping("/createFaq")
    public ResponseEntity<ResponseDTO> createFaq(@RequestBody FaqDataDTO faqDataDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            FaqDataDTO faqDataDTO1 = faqDataService.createQuestion(faqDataDTO);
            responseDTO.setMessage("faq created");
            responseDTO.setStatus(200);
            responseDTO.setData(faqDataDTO1);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            responseDTO.setStatus(400);
            responseDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/getAllFaqs")
    public ResponseEntity<ResponseDTO> getAllFaqs() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<FaqDataDTO> faqDataDTO1 = faqDataService.getAllFaqData();
            responseDTO.setMessage("success");
            responseDTO.setStatus(200);
            responseDTO.setData(faqDataDTO1);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            responseDTO.setStatus(400);
            responseDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

}
