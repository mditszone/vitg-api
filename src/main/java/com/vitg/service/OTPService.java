package com.vitg.service;

import org.springframework.stereotype.Service;

import com.vitg.dto.PhoneVerificationDTO;
import com.vitg.entity.PhoneVerification;

@Service
public interface OTPService {

	public int generateOTP();
	public String generateVerificationRef();
	public PhoneVerification savePhoneVerification(PhoneVerification phoneVerification);
	public void sendOTP(String otp,String phoneNumber);
	public boolean verifyOtp(PhoneVerificationDTO phoneVerificationDTO);
	public boolean validateOtp(String verificationRef,String phoneNumber);
}
