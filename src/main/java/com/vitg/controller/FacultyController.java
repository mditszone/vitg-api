package com.vitg.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.FacultyDTO;
import com.vitg.dto.PhoneVerificationDTO;
import com.vitg.dto.StudentDTO;
import com.vitg.dto.UserDTO;
import com.vitg.entity.Role;
import com.vitg.entity.User;
import com.vitg.exception.BadRequestException;
import com.vitg.exception.OTPException;
import com.vitg.repository.FacultyRepository;
import com.vitg.repository.RoleRepository;
import com.vitg.repository.UserRepository;
import com.vitg.service.FacultyService;
import com.vitg.service.FacultySubCourseService;
import com.vitg.service.OTPService;

@RestController
@CrossOrigin
@RequestMapping("/api/faculty")
public class FacultyController {

	@Autowired
	public FacultyService facultyService;

	@Autowired
	public FacultySubCourseService facultySubCourseService;

	@Autowired
	public FacultyRepository facultyRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public RoleRepository roleRepository;

	@GetMapping("/getAllFaculty")
	public List<FacultyDTO> getAlFaculty(){
		return facultyService.getAllFaculty();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable (value="id") int id){
		FacultyDTO facultyDTO = facultyService.getFacultyById(id);
		return new ResponseEntity<>(facultyDTO, HttpStatus.OK);
	}

	@PostMapping("/addFaculty")
	public ResponseEntity<FacultyDTO> addFaculty(@RequestBody FacultyDTO facultyDTO) {
		FacultyDTO facultyDTOResponse = facultyService.addFaculty(facultyDTO);
		return new ResponseEntity<>(facultyDTOResponse, HttpStatus.CREATED);
	}

	@PutMapping("/editFaculty")
	public ResponseEntity<FacultyDTO> updateFacultyInfo(@RequestBody @Valid FacultyDTO facultyDTO) throws BadRequestException {
		if (!facultyRepository.existsById(facultyDTO.getId())) {
			throw new BadRequestException("Failed to update FacultyInfo ");
		}

		facultyDTO = facultyService.updateFaculty(facultyDTO);
		return new ResponseEntity<>(facultyDTO, HttpStatus.OK);
	}
	@PutMapping("/facultyLogin")
	public ResponseEntity<FacultyDTO> loginTriner(@RequestBody @Valid FacultyDTO facultyDTO){
		facultyDTO = facultyService.loginTriner(facultyDTO);
		return new ResponseEntity<>(facultyDTO, HttpStatus.OK);
	}
	
//	@PutMapping("/facultyLogin/{userName}")
//	public ResponseEntity<FacultyDTO> loginTriner(@PathVariable (value = "userName")String userName){
//		FacultyDTO facultyDTO = facultyService.loginTriner(userName);
//		return new ResponseEntity<>(facultyDTO, HttpStatus.OK);
//	}
	
	@PostMapping("facultyLogin/verifyOtp")
	public ResponseEntity<FacultyDTO> validateFacultyPin(@RequestBody  FacultyDTO facultyDTO ) {

		boolean verified = facultyService.verifyPin(facultyDTO);

		if(verified) {
			FacultyDTO facultyDTOResponse = facultyService.getFacultyById(facultyDTO.getId()); 
			facultyDTOResponse.setFacultySubCourseIdList(facultySubCourseService.getSubCoursesByFacultyId(facultyDTO.getId()).stream().map(item -> item.getSubCourseId()).collect(Collectors.toList()));
			facultyDTOResponse.setRole("Faculty");
			System.out.println("facultyDTOResponse" +facultyDTOResponse);
			return new ResponseEntity<>(facultyDTOResponse, HttpStatus.CREATED);

		}else {
			throw new OTPException("Invalid PIN");
		}
	}
}