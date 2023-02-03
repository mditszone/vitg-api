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
@Table(name = "staff")
public class Staff implements Serializable {

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
	
	@Lob
	@Column(name = "prof_Pic", length = 1000)
	private byte[] profPic;

	@Column(name = "aadhar_number")
	private String aadharNumber;
	

	@Column(name = "pan_card_number")
	private String panCardNumber;
	
	@Column(name = "email")
	private String email;
	
	@Lob
	@Column(name = "aadhar_Pic",length = Integer.MAX_VALUE, nullable = true)
	private byte[] aadharPic;
	
	@Lob
	@Column(name = "pan_Card_Pic", length = 1000)
	private byte[] panCardPic;

	@Column(name = "registration_Status")
	private boolean registrationStatus;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Role.class)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	//private String roleName;


}
