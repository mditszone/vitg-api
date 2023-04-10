
package com.vitg.controller;
import java.util.List;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.FacultyTopicListResponseDTO;
import com.vitg.dto.StudentSubCourseAccessedData;
import com.vitg.dto.StudentTopicListResponseDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.dto.TopicDTO;
import com.vitg.exception.BadRequestException;
import com.vitg.exception.RecordNotFoundException;
import com.vitg.repository.TopicRepository;
import com.vitg.service.TopicService;
@RestController
@CrossOrigin
@RequestMapping("/api/topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private TopicRepository topicRepository;


	@PostMapping()
	public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topicDTO) {
		TopicDTO topicDTOResponse = topicService.createTopic(topicDTO);
		return new ResponseEntity<>(topicDTOResponse, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicDTO> getTopicById(@PathVariable(value = "id") int id){
		TopicDTO topicDTO = topicService.getTopicById(id);
		return new ResponseEntity<>(topicDTO, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("/AllTopics")
	public List<TopicDTO> getAllTopics() {
		return topicService.getAllTopics();
	}


	@PutMapping("/editTopic")
	public ResponseEntity<TopicDTO> updateTopicInfo( @RequestBody @Valid TopicDTO topicDTO)throws BadRequestException {
		if (!topicRepository.existsById(topicDTO.getId())) {
			throw new RecordNotFoundException("Topic details does'nt exist ");
		}
		topicDTO = topicService.updateTopic(topicDTO);
		return new ResponseEntity<>(topicDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteTopicById(@PathVariable(value = "id") int id) { 
		topicService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/getTopicListBySubCourseId")
	public ResponseEntity<List<TopicDTO>> getTopicListBySubCourseId(int subCourseId) {
		List<TopicDTO> topicDTOResponse = topicService.getTopicListBySubCourseId(subCourseId);
		return new ResponseEntity<>(topicDTOResponse, HttpStatus.OK);
	}

	//	@GetMapping("/getTopicListByStudentIdSubCourseId")
	//	public ResponseEntity<List<TopicDTO>> getTopicListByStudentIdSubCourseId(@RequestParam("studentId") int studentId,@RequestParam("subCourseId")int subCourseId){
	//		List<TopicDTO> topicDTOResponse = topicService.getTopicListByStudentIdSubCourseId(studentId,  subCourseId);
	//		return new ResponseEntity<>(topicDTOResponse, HttpStatus.OK);
	//	}

	@GetMapping("/getTopicListByStudentId")
	public ResponseEntity<List<StudentTopicListResponseDTO>> getTopicListByStudentId(@RequestParam (value = "studentId") int studentId,@RequestParam (value = "subCourseId") int subCourseId){
		List<StudentTopicListResponseDTO> response = topicService.getTopicListByStudentId(studentId,subCourseId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/getTopicsListByStudentId2")
	public ResponseEntity<List<StudentSubCourseAccessedData>> getTopicsListByStudentId2(@RequestParam (value = "subCourseId") int subCourseId, @RequestParam (value = "studentId") int studentId){
		List<StudentSubCourseAccessedData> response = topicService.getTopicsListByStudentId2(subCourseId, studentId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/getTopicListByStudenFacultytId")
	public ResponseEntity<List<FacultyTopicListResponseDTO>> getTopicListByFacultyId(@RequestParam (value = "facultyId") int facultyId,@RequestParam (value = "subCourseId") int subCourseId){
		List<FacultyTopicListResponseDTO> response = topicService.getTopicListByFacultyId(facultyId,subCourseId);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/getTopicsListByStudentId/{studentId}")
	public ResponseEntity<List<StudentSubCourseAccessedData>> getTopicsListByStudentId(@PathVariable (value = "studentId") int studentId){
		List<StudentSubCourseAccessedData> response = topicService.getTopicsListByStudentId(studentId);
		return new ResponseEntity<>(response,HttpStatus.OK);}
}
