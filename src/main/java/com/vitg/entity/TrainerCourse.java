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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trainer_course")
public class TrainerCourse implements Serializable{
	private static final long serialVersionUID = 3871845855908211918L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "id")
	private int id;
	
	@OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER,targetEntity = Trainer.class)
	@JoinColumn(name = "trainer_id", referencedColumnName = "id")
	@JoinColumn(name = "trainer_name", referencedColumnName = "name")
	private Trainer trainer;
	
	@OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER,targetEntity = Course.class)
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;
	
}
