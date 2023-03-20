package com.vitg.dto;


import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String type = "Bearer";
    private String token;

    Object user;
}
