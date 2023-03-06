
package com.vitg.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


import lombok.Data;

@Data
public class BatchDTO{
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;

	private String fee;

	private String duration;

	private String status;

	private String organizer;
	private TrainerCourseDTO trainerCourse;

}