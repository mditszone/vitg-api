
package com.vitg.controller;



import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.vitg.dto.TrackDTO;
import com.vitg.exception.BadRequestException;
import com.vitg.exception.RecordNotFoundException;

import com.vitg.repository.TrackRepository;

import com.vitg.service.TrackService;
@RestController
@CrossOrigin
@RequestMapping("/api/track")
public class TrackController {
	@Autowired
	private TrackService trackService;

	@Autowired
	private TrackRepository trackRepository;
	
	@PostMapping()
	public ResponseEntity<TrackDTO> createTrack(@RequestBody TrackDTO trackDTO) {

		System.out.println("inside create track" +trackDTO);
		TrackDTO trackDTOResponse = trackService.createTrack(trackDTO);

		return new ResponseEntity<>(trackDTOResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TrackDTO> getTrackById(@PathVariable(value = "id") int id){
		TrackDTO trackDTO = trackService.getTrackById(id);
		return new ResponseEntity<>(trackDTO, HttpStatus.OK);
	}

	@GetMapping("/AllTracks")
	public List<TrackDTO> getAllTrackss() {
		return trackService.getAllTracks();
	}

	
	@PutMapping("/editTrack")
	public ResponseEntity<TrackDTO> updateTrackInfo( @RequestBody @Valid TrackDTO trackDTO)

			throws BadRequestException {
		if (!trackRepository.existsById(trackDTO.getId())) {
			throw new RecordNotFoundException("Track details does'nt exist ");
		}
		trackDTO = trackService.updateTrack(trackDTO);
		return new ResponseEntity<>(trackDTO, HttpStatus.OK);
	}

}
