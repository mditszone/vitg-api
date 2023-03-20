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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facultySubcourse")
public class FacultySubCourse implements Serializable{

	private static final long serialVersionUID = 4130758031076098234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Faculty.class)
	@JoinColumn(name = "faculty_id", referencedColumnName = "id")
	private Faculty faculty;
	
	@OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = SubCourse.class)
	@JoinColumn(name = "subCourse_id", referencedColumnName = "id")
	private SubCourse subCourse;
}
