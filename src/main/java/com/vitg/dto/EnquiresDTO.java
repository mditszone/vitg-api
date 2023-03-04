package com.vitg.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class EnquiresDTO {
    private int id;

    private String name;

    private String email;

    private String phoneNumber;

    private String message;

    private String status;

    private String remarks;

}