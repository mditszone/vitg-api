package com.vitg.entity;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainer")
public class Trainer implements Serializable {

	private static final long serialVersionUID = -5583968848579257151L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number",unique = true)
	private String phoneNumber;

	@Column(name = "gender")
	private String gender;

	@Column(name = "email")
	private String email;

	@Column(name = "prof_Pic", length = 1000)
	@Lob
	private byte[] profPic;

	@Column(name = "aadhar_number")
	private String aadharNumber;

	@Column(name = "pan_card_number")
	private String panCardNumber;

	@Column(name = "aadhar_Pic", length = 1000)
	@Lob
	private byte[] aadharPic;

	@Column(name = "pan_Card_Pic", length = 1000)
	@Lob
	private byte[] panCardPic;

	@Column(name = "registration_Status")
	private boolean registrationStatus;

}
