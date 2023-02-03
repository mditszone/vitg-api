package com.vitg.service;

import com.vitg.dto.CourseDTO;
import java.util.List;


public interface CourseService {
	public CourseDTO getCourseById(int id);
	public void deleteById(int id);
	public CourseDTO createCourse(CourseDTO courseDTO);
	public CourseDTO updateCourse(CourseDTO courseDTO);
	public List<CourseDTO> getAllCourses();
}







