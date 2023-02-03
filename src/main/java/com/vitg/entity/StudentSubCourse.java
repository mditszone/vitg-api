package com.vitg.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "studentSubcourse")
public class StudentSubCourse implements Serializable{

	private static final long serialVersionUID = 4130758031076098234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Student.class)
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;
	
	@OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = SubCourse.class)
	@JoinColumn(name = "subCourse_id", referencedColumnName = "id")
	private SubCourse subCourse;

	
}
