package com.vitg.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitg.dto.BatchDTO;

import com.vitg.entity.Batch;

import org.modelmapper.ModelMapper;


@Component
public class BatchMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public BatchDTO batchToBatchDTO(Batch batch) {
		return  modelMapper.map(batch , BatchDTO.class);
	}

	public Batch batchDTOToBatch(BatchDTO batchDTO) {

		return modelMapper.map(batchDTO, Batch.class);
	}
}
