package com.vitg.dto;

import lombok.Data;

@Data
public class TrainerDTO {

	private int id;
	private String name;
	private String phoneNumber;
	private String gender;
	private byte[] profPic;
	private String email;
	private byte[] aadharPic;
	private byte[] panCardPic;
	private String aadharNumber;
	private String panCardNumber;
	private boolean registrationStatus;
	private byte[] image;
}
