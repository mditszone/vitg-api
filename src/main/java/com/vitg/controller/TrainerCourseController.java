package com.vitg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.ResponseDTO;
import com.vitg.dto.TrainerCourseDTO;
import com.vitg.service.TrainerCourseService;

@RestController
@CrossOrigin
@RequestMapping("/api/trainerCourse")
public class TrainerCourseController {
	
	@Autowired
	public TrainerCourseService trainerCourseService;

	@PostMapping("/selectTrainerCourses")
	public ResponseEntity<ResponseDTO> createCoursesOfTrainer(@RequestBody List<TrainerCourseDTO> trainerCourses){
	ResponseDTO responseDTO = trainerCourseService.createCoursesOfTrainer(trainerCourses);
	return new ResponseEntity<>(responseDTO,HttpStatus.OK);
	}
	
	@GetMapping("/getTrainerListByCourseId")
	public ResponseEntity<List<TrainerCourseDTO>> getTrainerListByCourseId(int courseId){
		List<TrainerCourseDTO> trainerDTOresponse = trainerCourseService.getTrainerListByCourseId(courseId);
		return new ResponseEntity<>(trainerDTOresponse, HttpStatus.OK);
	}
	
	@GetMapping("/getCourseListById")
	public ResponseEntity<List<TrainerCourseDTO>> getCourseListById(int id){
		List<TrainerCourseDTO> trainerDTOresponse = trainerCourseService.getCourseListById(id);
		return new ResponseEntity<>(trainerDTOresponse, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TrainerCourseDTO> getTrainerCourseById(@PathVariable (value="id") int id){
		TrainerCourseDTO trainerCourseDTO = trainerCourseService.getTrainerCourseById(id);
		return new ResponseEntity<>(trainerCourseDTO, HttpStatus.OK);
	}
}
