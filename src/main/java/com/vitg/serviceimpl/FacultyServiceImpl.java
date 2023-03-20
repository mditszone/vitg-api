package com.vitg.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitg.dto.FacultyDTO;
import com.vitg.entity.Faculty;
import com.vitg.entity.PhoneVerification;
import com.vitg.repository.FacultyRepository;
import com.vitg.service.FacultyService;
import com.vitg.service.OTPService;

@Service
public class FacultyServiceImpl implements FacultyService{

	@Autowired
	public FacultyRepository facultyRepository;
	
	@Autowired ModelMapper modelMapper;
	
	@Autowired
	public OTPService otpService;
	
	@Override
	public FacultyDTO addFaculty(FacultyDTO facultyDTO) {
		int pin = otpService.generateOTP();
		facultyDTO.setPin(pin);
		Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
		Faculty savedFaculty = facultyRepository.save(faculty);
		FacultyDTO facultyDTOResponse = modelMapper.map(savedFaculty, FacultyDTO.class);
		return facultyDTOResponse;
	}

	@Override
	public FacultyDTO getFacultyById(int id) {
		Faculty faculty = facultyRepository.findById(id);
		return modelMapper.map(faculty,FacultyDTO.class);
	}

	@Override
	public FacultyDTO updateFaculty(FacultyDTO facultyDTO) {
		Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
		Faculty facultyResponse = facultyRepository.save(faculty);
		FacultyDTO facultyDTOResponse = modelMapper.map(facultyResponse, FacultyDTO.class);
		return facultyDTOResponse;
	}

	@Override
	public FacultyDTO loginTriner(FacultyDTO facultyDTO) {
		int pin = otpService.generateOTP();
		facultyDTO.setPin(pin);
		Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
		facultyRepository.save(faculty);
		FacultyDTO facultyDTOResponse = new FacultyDTO();
		facultyDTOResponse.setId(faculty.getId());
		facultyDTOResponse.setUserName(faculty.getUserName());
		return facultyDTOResponse;
	}
	
//	@Override
//	public FacultyDTO loginTriner(String userName) {
//		int pin = otpService.generateOTP();
//		FacultyDTO facultyDTOResponse = new FacultyDTO();
//		facultyDTOResponse.setPin(pin);
//		//facultyDTOResponse.setUserName(userName);
//		Faculty faculty = modelMapper.map(facultyDTOResponse, Faculty.class);
//		facultyRepository.save(faculty);
//		System.out.println(facultyDTOResponse);
//		return facultyDTOResponse;
//		
//	}	
	
	@Override
	public List<FacultyDTO> getAllFaculty() {
		List<Faculty> facultyList = facultyRepository.findAll();
		List<FacultyDTO> facultyDTOList = new ArrayList<>();
		for(Faculty faculty:facultyList) {
			FacultyDTO facultyDTO = modelMapper.map(faculty,FacultyDTO.class);
			facultyDTOList.add(facultyDTO);
		}
		return facultyDTOList;
	}

	@Override
	public boolean verifyPin(FacultyDTO facultyDTO) {
		Faculty faculty = facultyRepository.findById(facultyDTO.getId());
		if(facultyDTO.getPin() == faculty.getPin() ) {
			return true;
		}
		return false;
	}	

}
