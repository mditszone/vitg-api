package com.vitg.service;

import java.util.List;

import com.vitg.dto.SubTopicConceptDTO;
import com.vitg.dto.TopicDTO;

public interface SubTopicConceptService {
	
	public SubTopicConceptDTO getSubTopicConceptById(int id);
	public void deleteById(int id);
	public SubTopicConceptDTO createSubTopicConcept(SubTopicConceptDTO subTopicConceptDTO);
	public SubTopicConceptDTO updateSubTopicConcept (SubTopicConceptDTO subTopicConceptDTO);
	public List<SubTopicConceptDTO> getAllSubTopicConcepts();
	public List<SubTopicConceptDTO> getSubTopicConceptListBySubTopicId(int subTopicId);

}
