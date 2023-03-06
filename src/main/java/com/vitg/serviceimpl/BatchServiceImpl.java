

package com.vitg.serviceimpl;


import java.util.ArrayList;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitg.dto.BatchDTO;
import com.vitg.dto.CourseDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.dto.SubTopicDTO;
import com.vitg.dto.TopicDTO;
import com.vitg.dto.TrainerCourseDTO;
import com.vitg.entity.Batch;
import com.vitg.entity.Course;
import com.vitg.entity.SubCourse;
import com.vitg.entity.SubTopic;
import com.vitg.entity.TrainerCourse;
import com.vitg.mappers.BatchMapper;
import com.vitg.repository.BatchRepository;
import com.vitg.repository.CourseRepository;
import com.vitg.repository.SubCourseRepository;
import com.vitg.repository.TrainerCourseRepository;
import com.vitg.service.BatchService;

@Service
public class BatchServiceImpl implements BatchService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private  TrainerCourseRepository  trainerCourseRepository;

	@Autowired
	private  SubCourseRepository  subCourseRepository;

	@Transactional
	public BatchDTO createBatch( BatchDTO  batchDTO) {

		TrainerCourse trainerCourse = trainerCourseRepository.findById(batchDTO.getTrainerCourse().getId());
		TrainerCourseDTO trainerCourseDTO= new TrainerCourseDTO();
		trainerCourseDTO.setId(trainerCourse.getId());
		batchDTO.setTrainerCourse(trainerCourseDTO);
		Batch  batch= modelMapper.map(batchDTO,  Batch.class);
		Batch savedBatch = batchRepository.save(batch);
		savedBatch.setTrainerCourse(trainerCourse);
		BatchDTO  batchDTOResponse=modelMapper.map(savedBatch,  BatchDTO.class);
		batchDTOResponse.setTrainerCourse(trainerCourseDTO);
		return  batchDTOResponse;
	}

	public BatchDTO getBatchById(int id) {
		Batch batch = batchRepository.findById(id);
		BatchDTO batchDTO = modelMapper.map(batch, BatchDTO.class);
		return batchDTO;
	}

	public List<BatchDTO> getAllBatches() {
		List<Batch>  batchList = batchRepository.findAll();
		List<BatchDTO> batchDTOList=new ArrayList<>();
		for(Batch batch:batchList) {
			//System.out.println(batch);
			BatchDTO batchDTO = modelMapper.map(batch, BatchDTO.class);
			batchDTOList.add(batchDTO);
		}
		return batchDTOList;
	}

	public BatchDTO updateBatch(BatchDTO batchDTO) {

		SubCourse subCourse = subCourseRepository.findById(batchDTO.getTrainerCourse().getId());
		SubCourseDTO subCourseDTO = new SubCourseDTO();
		if(subCourse!=null) {
			subCourseDTO.setId(subCourse.getId());
			subCourseDTO.setName(subCourse.getName());
		}

		Batch batch = modelMapper.map(batchDTO, Batch.class);
		Batch batchResponse = batchRepository.save(batch);
		BatchDTO batchDTOResponse=modelMapper.map(batchResponse, BatchDTO.class);
		return batchDTOResponse;

	}

	@Override
	public void deleteById(int id) {
		batchRepository.deleteById(id);
	}	

	@Override
	public List<BatchDTO> getBatchListBySubCourseId(int courseId) {
		List<Batch> batchList = batchRepository.findAll();
		List<BatchDTO> batchDTOList = new ArrayList<>();

		for(Batch batch: batchList) {
			if(batch.getTrainerCourse().getId() == courseId) {
				BatchDTO batchDTO = modelMapper.map(batch, BatchDTO.class);
				batchDTOList.add(batchDTO);
			}
		}
		System.out.println(batchDTOList);
		return batchDTOList;
	}


}