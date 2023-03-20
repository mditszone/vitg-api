package com.vitg.serviceimpl;

import java.util.Calendar;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitg.dto.PhoneVerificationDTO;
import com.vitg.entity.PhoneVerification;
import com.vitg.repository.PhoneVerificationRepository;
import com.vitg.service.OTPService;

@Service
public class OTPServiceImpl  implements OTPService  {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PhoneVerificationRepository phoneVerificationRepository;

	@Autowired
	private AwsSesServiceImpl awsSesService;

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int EXPIRE_MINS = 5;

	public int generateOTP() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return otp;
	}

	public String generateVerificationRef() {
		int count =10; // Generating a 10-letter verificationRef
		StringBuilder stringBuilder = new StringBuilder();
		while(count-- != 0) {
			int character = (int) (Math.random()*ALPHA_NUMERIC_STRING.length());
			stringBuilder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return stringBuilder.toString();
	}

	public PhoneVerification savePhoneVerification(PhoneVerification phoneVerification) {
		phoneVerification.setExpiryTime(generateExpiryTime());
		logger.info("Saving: {} ", phoneVerification);
		return phoneVerificationRepository.save(phoneVerification);
	}

	private long generateExpiryTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, EXPIRE_MINS);
		return calendar.getTimeInMillis();
	}
	private long getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.getTimeInMillis();
	}

	public void sendOTP(String otp,String phoneNumber){
		String otpStr = " Vitg App : " + otp ;
		System.out.println("inside send otp " + otp);
		//awsSesService.sendSMSMessage(otpStr,phoneNumber);
	}

	@Transactional
	public boolean verifyOtp(PhoneVerificationDTO phoneVerificationDTO){

		PhoneVerification phoneVerification = phoneVerificationRepository.findByVerificationRef(phoneVerificationDTO.getVerificationRef());

		if (phoneVerification != null  &&
				phoneVerification.getVerificationRef().equals(phoneVerificationDTO.getVerificationRef()) && 
				phoneVerificationDTO.getOtp().equals(phoneVerification.getOtp()) && 
				((phoneVerification.getExpiryTime()) - getCurrentTime()) <300000 && 
				phoneVerification.getPhoneNumber().equals(phoneVerificationDTO.getPhoneNumber())){
			//return phoneVerification.getVerificationRef();
			phoneVerificationRepository.deleteByVerificationRef(phoneVerificationDTO.getVerificationRef());
			return true;
		}
		return false;


	}

	public boolean validateOtp(String verificationRef,String phoneNumber){
		PhoneVerification phoneVerification = phoneVerificationRepository.findByVerificationRef(verificationRef);
		long expTime = phoneVerification.getExpiryTime();
		if (expTime - getCurrentTime() <600000 && phoneVerification.getPhoneNumber().equals(phoneNumber)){
			phoneVerificationRepository.deleteByVerificationRef(verificationRef);
			return true;
		}
		return false;
	}
}
