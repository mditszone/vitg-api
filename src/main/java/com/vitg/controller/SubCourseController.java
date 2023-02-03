
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
import org.springframework.web.bind.annotation.RestController;
import com.vitg.dto.CourseDTO;
import com.vitg.dto.SubCourseDTO;
import com.vitg.repository.SubCourseRepository;
import com.vitg.service.SubCourseService;
import com.vitg.serviceimpl.SubCourseServiceImpl;
import com.vitg.exception.BadRequestException;
import com.vitg.exception.RecordNotFoundException;
@RestController
@CrossOrigin
@RequestMapping("/api/subCourse")
public class SubCourseController {

	@Autowired
	private SubCourseService subCourseService;

	@Autowired
	private SubCourseRepository subCourseRepository;

	@PostMapping()
	public ResponseEntity<SubCourseDTO> createSubCourse(@RequestBody SubCourseDTO subCourseDTO) {
		SubCourseDTO subCourseDTOResponse = subCourseService.createSubCourse(subCourseDTO);
		return new ResponseEntity<>(subCourseDTOResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubCourseDTO> getSubCourseById(@PathVariable(value = "id") int id){
		SubCourseDTO subCourseDTO = subCourseService.getSubCourseById(id);
		return new ResponseEntity<>(subCourseDTO, HttpStatus.OK);
	}


	@GetMapping("/AllSubCourses")
	public List<SubCourseDTO> getAllSubCourses() {
		return subCourseService.getAllSubCourses();
	}

	@PutMapping("/editSubCourse")

	public ResponseEntity<SubCourseDTO> updateSubCourseInfo(@RequestBody @Valid SubCourseDTO subCourseDTO)throws BadRequestException {
		
		if (!subCourseRepository.existsById(subCourseDTO.getId())) {
			throw new RecordNotFoundException("SubCourse details does'nt exist ");
		}
		subCourseDTO = subCourseService.updateSubCourse(subCourseDTO);
		return new ResponseEntity<>(subCourseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteSubCourseById(@PathVariable(value = "id") int id) { 
		subCourseService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/getSubCourseByCourseId")
	public ResponseEntity<List<SubCourseDTO>> getSubCourseListByCourseId(int courseId) {
		List<SubCourseDTO> subCourseDTOResponse = subCourseService.getSubCourseListByCourseId(courseId);
		return new ResponseEntity<>(subCourseDTOResponse, HttpStatus.OK);
	}
}
