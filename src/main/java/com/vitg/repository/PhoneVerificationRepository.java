package com.vitg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitg.entity.PhoneVerification;

public interface PhoneVerificationRepository extends JpaRepository<PhoneVerification, String>{

	public PhoneVerification findByPhoneNumber(String phoneNumber);
	public PhoneVerification findByVerificationRef(String verificationRef);
	public void deleteByVerificationRef(String verificationRef);
	public PhoneVerification findByPhoneNumberAndOtp(String phoneNumber,String opt);
}
