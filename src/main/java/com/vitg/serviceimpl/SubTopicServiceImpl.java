

package com.vitg.serviceimpl;

import java.util.ArrayList;


import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vitg.dto.SubTopicDTO;
import com.vitg.entity.SubTopic;
import com.vitg.repository.SubTopicRepository;
import com.vitg.repository.TopicRepository;
import com.vitg.service.SubTopicService;



@Service
public  class SubTopicServiceImpl implements SubTopicService{


	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SubTopicRepository subTopicRepository;

	@Autowired
	private TopicRepository topicRepository;


	@Transactional
	public  SubTopicDTO createSubTopic( SubTopicDTO  subTopicDTO) {

		topicRepository.findById(subTopicDTO.getTopic().getId());

		SubTopic  subTopic = modelMapper.map(subTopicDTO,  SubTopic.class);
		SubTopic savedSubTopic=  subTopicRepository.save( subTopic);
		SubTopicDTO  subTopicDTOResponse = modelMapper.map(savedSubTopic,  SubTopicDTO.class);
		return  subTopicDTOResponse;
	}

	public SubTopicDTO getSubTopicById(int id) {
		SubTopic subTopic = subTopicRepository.findById(id);
		SubTopicDTO subTopicDTO = modelMapper.map(subTopic, SubTopicDTO.class);
		return subTopicDTO;
	}

	public List<SubTopicDTO> getAllSubTopics(){
		List<SubTopic>  subTopicList=subTopicRepository.findAll();
		List<SubTopicDTO> subTopicDTOList=new ArrayList<>();
		for(SubTopic subTopic:subTopicList) {
			SubTopicDTO subTopicDTO=modelMapper.map(subTopic, SubTopicDTO.class);
			subTopicDTOList.add(subTopicDTO);
		}

		return subTopicDTOList;
	}
	public SubTopicDTO updateSubTopic(SubTopicDTO subTopicDTO) {
		SubTopic subTopic=(SubTopic) modelMapper.map(subTopicDTO, SubTopic.class);
		SubTopic subTopicResponse=subTopicRepository.save(subTopic);
		SubTopicDTO subTopicDTOResponse=(SubTopicDTO) modelMapper.map(subTopicResponse, SubTopicDTO.class);
		return subTopicDTOResponse;

	}

	@Override
	public void deleteById(int id) {
		subTopicRepository.deleteById(id);
	}

	@Override
	public List<SubTopicDTO> getSubTopicListByTopicId(int topicId) {
		List<SubTopic> subTopicList = subTopicRepository.findAll();
		List<SubTopicDTO> subTopicDTOList = new ArrayList<>();

		for(SubTopic subTopic: subTopicList) {
			if(subTopic.getTopic().getId() == topicId) {
				SubTopicDTO subTopicDTO = modelMapper.map(subTopic, SubTopicDTO.class);
				subTopicDTOList.add(subTopicDTO);
			}
		}
		return subTopicDTOList;
	}	

}