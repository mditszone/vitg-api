package com.vitg.dto;

import lombok.Data;

@Data
public class PhoneVerificationDTO {

	private String phoneNumber;
	private String otp;
	private String verificationRef;
}
