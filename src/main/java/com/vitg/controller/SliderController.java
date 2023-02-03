package com.vitg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.CourseDTO;
import com.vitg.dto.SliderDTO;
import com.vitg.dto.StaffDTO;
import com.vitg.repository.SliderRepository;
import com.vitg.service.SliderService;
@RestController
@CrossOrigin
@RequestMapping("/api/slider")
public class SliderController {

	@Autowired
	private SliderService sliderService;

	@Autowired
	private SliderRepository sliderRepository;


	@PostMapping("/upload_image")
	public ResponseEntity<SliderDTO> addImage(@RequestBody SliderDTO sliderDTO) {
		System.out.println(sliderDTO);
		SliderDTO sliderDTOResponse = sliderService.addImage(sliderDTO);

		return new ResponseEntity<>(sliderDTOResponse, HttpStatus.CREATED);
	}


	@GetMapping("/{id}")
	public ResponseEntity<SliderDTO> getSliderById(@PathVariable(value = "id") int id) { 
		sliderRepository.findById(id);
		SliderDTO sliderDTO = sliderService.getSliderById(id);
		return new ResponseEntity<>(sliderDTO, HttpStatus.OK);
	} 

	@GetMapping("/getAllSlider")
	public List<SliderDTO> getAllSlider() {
		return sliderService.getAllSlider();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCourseById(@PathVariable(value = "id") int id) { 
		sliderService.deleteById(id);
		return ResponseEntity.ok().build();

	}
}
