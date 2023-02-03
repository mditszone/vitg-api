package com.vitg.dto;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String phoneNumber;
	StaffDTO vitgStaffDTO;
	TrainerDTO trainerDTO;
	StudentDTO studentDTO;
	private byte[] image;
}
