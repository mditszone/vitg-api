
package com.vitg.entity;
import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

//import org.joda.time.LocalDate;
//import org.joda.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batch")
public class Batch  implements Serializable{

	private static final long serialVersionUID = 4130758031076098234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private int id;	

	@Column(name = "name")
	private String name;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;

	@Column(name = "organizer")
	private String organizer;

	@ManyToOne( targetEntity = TrainerCourse.class)
	@JoinColumn(name = "trainerCourse_id", referencedColumnName = "id")
	private TrainerCourse trainerCourse;


}