package com.vitg.controller;

import java.util.List;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.CourseDTO;
import com.vitg.dto.TrainerCourseDTO;
import com.vitg.dto.TrainerDTO;
import com.vitg.entity.User;
import com.vitg.exception.BadRequestException;
import com.vitg.repository.TrainerRepository;
import com.vitg.repository.UserRepository;
import com.vitg.service.TrainerCourseService;
import com.vitg.service.TrainerService;

@RestController
@CrossOrigin
@RequestMapping("/api/trainer")
public class TrainerController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private TrainerRepository trainerRepository;
	
	//Get TrainerById

	@GetMapping("/{id}")
	public ResponseEntity<TrainerDTO> getTrainerById(@PathVariable(value = "id") int id) { 
		TrainerDTO trainerDTO = trainerService.getTrainerById(id);
		return new ResponseEntity<>(trainerDTO, HttpStatus.OK);
	} 
	
	// Update Trainer
	
	@PutMapping("/editTrainer")
	public ResponseEntity<TrainerDTO> updateTrainerInfo(@RequestBody @Valid TrainerDTO trainerDTO) throws BadRequestException {

		if (!trainerRepository.existsById(trainerDTO.getId())) {
			throw new BadRequestException("Failed to update TrainerInfo ");
		}
		User user = userRepository.findByPhoneNumber(trainerDTO.getPhoneNumber());
		userRepository.save(user);
		
		trainerDTO.setRegistrationStatus(true);
		trainerDTO = trainerService.updateTrainer(trainerDTO);

		return new ResponseEntity<>(trainerDTO, HttpStatus.OK);
	}

	// Get AllTrainers
	
	@GetMapping("/getAllTrainers")
	public List<TrainerDTO> getAllTrainers() {
		return trainerService.getAllTrainers();
	}
	
}
