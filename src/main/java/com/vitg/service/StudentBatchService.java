package com.vitg.service;

import com.vitg.dto.EmailDTO;
import com.vitg.dto.ResponseDTO;
import com.vitg.dto.SmsDTO;

import java.util.List;

public interface StudentBatchService {
    public List<String> getStudentsPhoneNumberByBatchId(int batchId);

    public List<String> getStudentsEmailsByBatchId(int batchId);

    public boolean isMessageSentPreviously(int batchId);


    public ResponseDTO sendSMStoRegisterStudentsAndTrainer(SmsDTO smsDTO);

    public ResponseDTO sendEmailToRegisterStudentsAndTrainer(EmailDTO emailDTO);

}
