package com.vitg.service;

import java.util.List;

import com.vitg.dto.ResponseDTO;
import com.vitg.dto.TrainerCourseDTO;

public interface TrainerCourseService {
	
	public TrainerCourseDTO getTrainerCourseById(int id);
	public ResponseDTO createCoursesOfTrainer(List<TrainerCourseDTO> trainerCourses);
	public List<TrainerCourseDTO> getTrainerListByCourseId(int courseId);
	public List<TrainerCourseDTO> getCourseListById(int id);
	public List<TrainerCourseDTO> getAllTrainerCourse();
}
