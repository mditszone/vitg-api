package com.vitg.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.FacultySubCourseDTO;
import com.vitg.dto.ResponseDTO;
import com.vitg.entity.Faculty;
import com.vitg.entity.FacultySubCourse;
import com.vitg.entity.SubCourse;
import com.vitg.repository.FacultyRepository;
import com.vitg.repository.FacultySubCourseRepository;
import com.vitg.repository.SubCourseRepository;
import com.vitg.service.FacultySubCourseService;

@Service
public class FacultySubCourseServiceImpl implements FacultySubCourseService{

	@Autowired
	public FacultySubCourseRepository facultySubCourseRepository;
	
	@Autowired
	public FacultyRepository facultyRepository;
	
	@Autowired
	public SubCourseRepository subCourseRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<FacultySubCourseDTO> getFacultyIdBySubCourseId(int subCourseId) {
		List<FacultySubCourse> facultySubCourseList = facultySubCourseRepository.findAll();
		List<FacultySubCourseDTO> facultySubCourseDTOList = new ArrayList<>();
		
		for (FacultySubCourse selected: facultySubCourseList) {
			if (selected.getSubCourse().getId() == subCourseId) {
				FacultySubCourseDTO dto = modelMapper.map(selected, FacultySubCourseDTO.class);
				facultySubCourseDTOList.add(dto);
			}
		}
		return facultySubCourseDTOList;
	}

	@Override
	public ResponseDTO updateSelectedSubCourses(List<FacultySubCourseDTO> selectedSubCourses) {
		for(FacultySubCourseDTO selected: selectedSubCourses) {
			FacultySubCourse facultySubCourse = new FacultySubCourse();
			Faculty faculty = facultyRepository.findById(selected.getFacultyId());
			SubCourse subCourse = subCourseRepository.findById(selected.getSubCourseId());
			facultySubCourse.setFaculty(faculty);
			facultySubCourse.setSubCourse(subCourse);
			facultySubCourseRepository.save(facultySubCourse);
		}
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("selected subCourses updated");
		
		return responseDTO;
	}

	@Override
	public List<FacultySubCourseDTO> getSubCoursesByFacultyId(int facultyId) {
		List<FacultySubCourse> subCourseList = facultySubCourseRepository.findAll();
		List<FacultySubCourseDTO> subCourseDTOList = new ArrayList<>();
		for (FacultySubCourse selected: subCourseList) {
			if (selected.getFaculty().getId() == facultyId) {
				FacultySubCourseDTO dto = modelMapper.map(selected, FacultySubCourseDTO.class);
				subCourseDTOList.add(dto);
			}
		}
		return subCourseDTOList;
	}
	
	@Override
	public FacultySubCourseDTO getSubCoursesByFacultyIdAndSubCourseId(int facultyId, int subCourseId) {
		FacultySubCourse facultySubCourse = facultySubCourseRepository.findByFacultyIdAndSubCourseId(facultyId, subCourseId);
		if (facultySubCourse == null) {
			return null;
		} else {
			FacultySubCourse facultySubCourseResponse = facultySubCourseRepository.findById(subCourseId);
			return modelMapper.map(facultySubCourseResponse, FacultySubCourseDTO.class);
		}
	}
}
