package com.vitg.service;

import com.vitg.dto.EnquiresDTO;
import com.vitg.dto.Mail;
import com.vitg.dto.ResponseDTO;
import com.vitg.entity.Enquires;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnquiresService {
    ResponseDTO createEnquiry(EnquiresDTO enquiresDTO);
    List<EnquiresDTO> getAllEnquires();

    EnquiresDTO getEnquiryById(int id);

    ResponseDTO sendEmail(String subject, String to, String message);

}
