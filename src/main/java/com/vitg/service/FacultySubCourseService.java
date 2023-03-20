package com.vitg.service;

import java.util.List;

import com.vitg.dto.FacultySubCourseDTO;
import com.vitg.dto.ResponseDTO;

public interface FacultySubCourseService {

	public List<FacultySubCourseDTO> getFacultyIdBySubCourseId(int subCourseId);
	public List<FacultySubCourseDTO > getSubCoursesByFacultyId(int facultyId);
	public ResponseDTO updateSelectedSubCourses(List<FacultySubCourseDTO> FacultySubCourseDTO);
    public FacultySubCourseDTO getSubCoursesByFacultyIdAndSubCourseId(int facultyId, int subCourseId);
}
