
package com.vitg.controller;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vitg.dto.SubCourseDTO;
import com.vitg.dto.SubTopicDTO;
import com.vitg.dto.TopicDTO;
import com.vitg.exception.BadRequestException;
import com.vitg.exception.RecordNotFoundException;
import com.vitg.repository.SubTopicRepository;
import com.vitg.service.SubTopicService;

@RestController
@CrossOrigin
@RequestMapping("/api/subTopic")
public class SubTopicController {

	@Autowired
	private SubTopicService subTopicService;

	@Autowired
	private SubTopicRepository subTopicRepository;

	@PostMapping()
	public ResponseEntity<SubTopicDTO> createSubTopic(@RequestBody SubTopicDTO subTopicDTO) {

		System.out.println("inside create subTopic" +subTopicDTO);
		SubTopicDTO subTopicDTOResponse = subTopicService.createSubTopic(subTopicDTO);
		return new ResponseEntity<>(subTopicDTOResponse, HttpStatus.CREATED);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubTopicDTO> getSubTopicById(@PathVariable(value = "id") int id){
		SubTopicDTO subTopicDTO = subTopicService.getSubTopicById(id);
		return new ResponseEntity<>(subTopicDTO, HttpStatus.OK);
	}

	@GetMapping("/AllSubTopics")
	public List<SubTopicDTO> getAllSubTopics() {
		return subTopicService.getAllSubTopics();
	}
	
	@PutMapping("/editSubTopic")
	public ResponseEntity<SubTopicDTO> updateSubTopicInfo( @RequestBody @Valid SubTopicDTO subTopicDTO)
			throws BadRequestException {
		if (!subTopicRepository.existsById(subTopicDTO.getId())) {
			throw new RecordNotFoundException("SubTopic details does'nt exist ");
		}
		subTopicDTO = subTopicService.updateSubTopic(subTopicDTO);
		return new ResponseEntity<>(subTopicDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteSubTopicById(@PathVariable(value = "id") int id) { 
		subTopicService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/getsubTopicListByTopicId")
	public ResponseEntity<List<SubTopicDTO>> getSubTopicListByTopicId(int topicId) {
		List<SubTopicDTO> subTopicDTOResponse = subTopicService.getSubTopicListByTopicId(topicId);
		return new ResponseEntity<>(subTopicDTOResponse, HttpStatus.OK);
	}
}