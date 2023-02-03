package com.vitg.service;

import java.util.List;
import com.vitg.dto.SubCourseDTO;

public interface SubCourseService {
	
	public SubCourseDTO getSubCourseById(int id);
	public void deleteById(int id);
	public SubCourseDTO createSubCourse(SubCourseDTO subCourseDTO);
	public SubCourseDTO updateSubCourse (SubCourseDTO subCourseDTO);
	public List<SubCourseDTO> getAllSubCourses();
	public List<SubCourseDTO> getSubCourseListByCourseId(int courseId);

}