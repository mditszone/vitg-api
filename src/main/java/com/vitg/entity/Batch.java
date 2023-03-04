
package com.vitg.entity;
import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "fee")
	private String fee;

	@Column(name = "duration")
	private String duration;

	@Column(name = "status")
	private String status;

	@Column(name = "organizer")
	private String organizer;

	@ManyToOne( targetEntity = TrainerCourse.class)
	@JoinColumn(name = "trainerCourse_id", referencedColumnName = "id")
	private TrainerCourse trainerCourse;

	@OneToOne( targetEntity = Trainer.class)
	@JoinColumn(name = "trainer_id", referencedColumnName = "id")
	private TrainerCourse trainerId;

	@OneToOne( targetEntity = Course.class)
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private TrainerCourse courseId;

}