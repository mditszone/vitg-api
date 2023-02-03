package com.vitg.service;

import java.util.List;

import com.vitg.dto.TrainerCourseDTO;
import com.vitg.dto.TrainerDTO;

public interface TrainerService {

	public TrainerDTO getTrainerById(int id);
	public void deleteById(int id);
	public TrainerDTO updateTrainer(TrainerDTO trainerDTO);
	public List<TrainerDTO> getAllTrainers();
	
}
