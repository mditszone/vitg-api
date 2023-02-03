
package com.vitg.serviceimpl;

import java.util.ArrayList;


import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vitg.dto.SubTopicConceptDTO;
import com.vitg.entity.SubTopicConcept;
import com.vitg.repository.SubTopicConceptRepository;
import com.vitg.service.SubTopicConceptService;


@Service
public  class SubTopicConceptServiceImpl implements SubTopicConceptService{


	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubTopicConceptRepository subTopicConceptRepository;

	@Transactional
	public SubTopicConceptDTO createSubTopicConcept(SubTopicConceptDTO subTopicConceptDTO) {
		
		SubTopicConcept subTopicConcept = modelMapper.map(subTopicConceptDTO, SubTopicConcept.class);
		SubTopicConcept savedSubTopicConcept = subTopicConceptRepository.save(subTopicConcept);
		SubTopicConceptDTO subTopicConceptDTOResponse = modelMapper.map(savedSubTopicConcept, SubTopicConceptDTO.class);

		return subTopicConceptDTOResponse;
	}
	public SubTopicConceptDTO getSubTopicConceptById(int id) {

		SubTopicConcept subTopicConcept = subTopicConceptRepository.findById(id);
		SubTopicConceptDTO subTopicConceptDTO = modelMapper.map(subTopicConcept, SubTopicConceptDTO.class);
		return subTopicConceptDTO;

	}

	public List<SubTopicConceptDTO> getAllSubTopicConcepts(){
		
		List<SubTopicConcept>  subTopicConceptList = subTopicConceptRepository.findAll();
		List<SubTopicConceptDTO> subTopicConceptDTOList = new ArrayList<>();
		for(SubTopicConcept subTopicConcept:subTopicConceptList) {
			SubTopicConceptDTO subTopicConceptDTO = modelMapper.map(subTopicConcept, SubTopicConceptDTO.class);
			subTopicConceptDTOList.add(subTopicConceptDTO);
		}

		return subTopicConceptDTOList;
	}
	public SubTopicConceptDTO updateSubTopicConcept(SubTopicConceptDTO subTopicConceptDTO) {
		
		SubTopicConcept subTopicConcept=(SubTopicConcept) modelMapper.map(subTopicConceptDTO, SubTopicConcept.class);
		SubTopicConcept subTopicConceptResponse=subTopicConceptRepository.save(subTopicConcept);
		SubTopicConceptDTO subTopicConceptDTOResponse=(SubTopicConceptDTO) modelMapper.map(subTopicConceptResponse, SubTopicConceptDTO.class);
		return subTopicConceptDTOResponse;

	}

	@Override
	public void deleteById(int id) {
		subTopicConceptRepository.deleteById(id);
	}

	@Override
	public List<SubTopicConceptDTO> getSubTopicConceptListBySubTopicId(int subTopicId) {
		
		List<SubTopicConcept> subTopicConceptList = subTopicConceptRepository.findAll();
		List<SubTopicConceptDTO> subTopicConceptDTOList = new ArrayList<>();

		for(SubTopicConcept subTopicConcept: subTopicConceptList) {
			if(subTopicConcept.getSubTopic().getId() == subTopicId) {
				SubTopicConceptDTO subTopicConceptDTO = modelMapper.map(subTopicConcept, SubTopicConceptDTO.class);
				subTopicConceptDTOList.add(subTopicConceptDTO);
			}
		}
		return subTopicConceptDTOList;
	}

}