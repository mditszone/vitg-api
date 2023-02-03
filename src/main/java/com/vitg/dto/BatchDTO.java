
package com.vitg.dto;

import java.time.LocalDate;
import java.time.LocalTime;



import lombok.Data;

@Data
public class BatchDTO{
	private int id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String organizer;
	private TrainerCourseDTO trainerCourse;
}