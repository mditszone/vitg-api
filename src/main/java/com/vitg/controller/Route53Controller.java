package com.vitg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitg.dto.CourseDTO;
import com.vitg.dto.ResponseDTO;
import com.vitg.service.Route53Service;

@RestController
@CrossOrigin
@RequestMapping("/api/route53")
public class Route53Controller {

	@Autowired 
	private Route53Service route53Service;
	
	@GetMapping("/create/{subDomain}")
	public ResponseEntity<ResponseDTO> createSubDomain(@PathVariable String  subDomain) {
		try {
			ResponseDTO responseDTO = route53Service.createSubDomain(subDomain);
			return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
		}
		catch(Exception e) {
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setStatus(400);
			responseDTO.setMessage(e.getMessage());
			return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
		}
		
	}
}
