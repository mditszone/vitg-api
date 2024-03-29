package com.vitg.dto;

import java.util.List;

import com.vitg.entity.Role;

import lombok.Data;

@Data
public class StudentDTO {

	private int id;
	private String name;
	private String phoneNumber;
	private String gender;
	private String email;
	private byte[] profPic;
	private boolean registrationStatus;
	private byte[] image;
	private List<Integer> studentSubCourseIdList;
	private String role;
	private String password;
}
