package com.vitg.service;

import com.vitg.dto.Mail;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public interface EmailService {

    public boolean sendEmail(Mail mail);

}
