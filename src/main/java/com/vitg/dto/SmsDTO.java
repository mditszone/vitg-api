package com.vitg.dto;

import lombok.Data;

import java.util.List;

@Data
public class SmsDTO {
    int batchId;

    int trainerId;
    String message;
    boolean sendStudents;
    boolean sendTrainer;

    List<String> emails;

}
