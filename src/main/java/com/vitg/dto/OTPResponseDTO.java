package com.vitg.dto;

import lombok.Data;

@Data
public class OTPResponseDTO {

	String phoneNumber;
	String StatusMessage;
	private String verificationRef;
}
