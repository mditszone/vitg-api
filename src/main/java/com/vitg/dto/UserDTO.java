package com.vitg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String token;
	private String phoneNumber;
	StaffDTO vitgStaffDTO;
	TrainerDTO trainerDTO;
	StudentDTO studentDTO;
	private byte[] image;


	@JsonIgnore
	private String password;
}
