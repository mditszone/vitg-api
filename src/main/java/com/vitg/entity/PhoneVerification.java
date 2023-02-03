package com.vitg.entity;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="phone_verification")
public class PhoneVerification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String phoneNumber;
	
	private String verificationRef;
	
	private String otp;
	
	private long expiryTime;
	
}
