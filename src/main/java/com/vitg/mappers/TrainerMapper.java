package com.vitg.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitg.dto.TrainerDTO;
import com.vitg.entity.Trainer;

@Component
public class TrainerMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public TrainerDTO trainerToTrainerDTO(Trainer trainer) {
		return modelMapper.map(trainer,TrainerDTO.class);
	}

	public Trainer TrainerDTOToTrainer(TrainerDTO trainerDTO) {

		return modelMapper.map(trainerDTO, Trainer.class);
	}
}
