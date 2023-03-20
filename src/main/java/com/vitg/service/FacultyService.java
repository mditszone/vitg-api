package com.vitg.service;

import java.util.List;

import com.vitg.dto.FacultyDTO;

public interface FacultyService {

	public FacultyDTO addFaculty(FacultyDTO facultyDTO);
	public List<FacultyDTO> getAllFaculty();
	public FacultyDTO getFacultyById(int id);
	public FacultyDTO updateFaculty(FacultyDTO facultyDTO);
	public FacultyDTO loginTriner(FacultyDTO facultyDTO);
//	public FacultyDTO loginTriner(String userName);
	public boolean verifyPin(FacultyDTO facultyDTO);
}
