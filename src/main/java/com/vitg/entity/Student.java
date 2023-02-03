package com.vitg.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable {


	private static final long serialVersionUID = -5583968848579257151L;

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "phone_number",unique = true)
	private String phoneNumber;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "prof_Pic", length = 1000)
	@Lob
	private byte[] profPic;

	@Column(name = "email")
	private String email;

	@Column(name = "registration_Status")
	private boolean registrationStatus;
	
	
//	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Role.class)
//	@JoinColumn(name = "role_id", referencedColumnName = "id")
//	private Role role;
//
//	private String roleName;
	
//	@ManyToOne( targetEntity = Course.class)
//	@JoinColumn(name = "course_id", referencedColumnName = "id")
//	private Course course;
	
}
