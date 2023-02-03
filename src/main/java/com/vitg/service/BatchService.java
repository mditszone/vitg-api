
package com.vitg.service;

import com.vitg.dto.BatchDTO;
import com.vitg.dto.TopicDTO;
import com.vitg.dto.TrainerDTO;

import java.util.List;


public interface BatchService {
	public BatchDTO getBatchById(int id);
	public void deleteById(int id);
	public BatchDTO createBatch(BatchDTO batchDTO);
	public BatchDTO updateBatch(BatchDTO batchDTO);
	public List<BatchDTO> getAllBatches();
	//public List<BatchDTO> getBatchListByTrainerId(int trainerId);
	public List<BatchDTO> getBatchListBySubCourseId(int subCourseId);
}

