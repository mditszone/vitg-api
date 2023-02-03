package com.vitg.service;

import org.springframework.stereotype.Service;

@Service
public interface AwsSesService {
	
	public void sendSMSMessage(String message, String phoneNumber) ;

}
