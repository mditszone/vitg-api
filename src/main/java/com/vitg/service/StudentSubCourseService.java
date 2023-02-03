package com.vitg.service;

import java.util.List;

import com.vitg.dto.ResponseDTO;
import com.vitg.dto.StudentSubCourseDTO;
import com.vitg.dto.SubCourseDTO;

public interface StudentSubCourseService {

	public List<StudentSubCourseDTO> getStudentIdBySubCourseId(int subCourseId);
	public List<StudentSubCourseDTO > getSubCoursesByStudentId(int studentId);
	public ResponseDTO updateSelectedSubCourses(List<StudentSubCourseDTO> StudentSubCourseDTO);
	//public SubCourseDTO getSubCoursesByStudentIdAndSubCourseId(int studentId, int subCourseId);
	public StudentSubCourseDTO getSubCoursesByStudentIdAndSubCourseId(int studentId, int subCourseId);
}
