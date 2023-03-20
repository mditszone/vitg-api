package com.vitg.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailDTO {
    int batchId;

    int trainerId;
    String message;
    boolean sendStudents;
    boolean sendTrainer;

    String subject;

    String url;

    List<String> emails;

}
