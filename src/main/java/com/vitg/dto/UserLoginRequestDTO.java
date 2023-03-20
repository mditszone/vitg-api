package com.vitg.dto;


import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String phoneNumber;
    private String password;
}
