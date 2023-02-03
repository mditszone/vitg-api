
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

import com.vitg.dto.BatchDTO;
import com.vitg.dto.TrainerDTO;
import com.vitg.entity.User;
import com.vitg.exception.BadRequestException;
import com.vitg.exception.RecordNotFoundException;
import com.vitg.repository.BatchRepository;

import com.vitg.service.BatchService;

@RestController
@CrossOrigin
@RequestMapping("/api/batch")
public class BatchController {
	
	@Autowired
	private BatchService batchService;

	@Autowired
	private BatchRepository batchRepository;
	
	@PostMapping("/createBatch")
	public ResponseEntity<BatchDTO> createBatch(@RequestBody BatchDTO batchDTO) {
		BatchDTO batchDTOResponse = batchService.createBatch(batchDTO);
		return new ResponseEntity<>(batchDTOResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BatchDTO> getBatchById(@PathVariable(value = "id") int id){
		BatchDTO batchDTO = batchService.getBatchById(id);
		return new ResponseEntity<>(batchDTO, HttpStatus.OK);
	}
	
	@GetMapping("/AllBatches")
	public List<BatchDTO> getAllBatches() {
		return batchService.getAllBatches();
	}
	
	@PutMapping("/editBatch")
	public ResponseEntity<BatchDTO> updateBatchInfo(@RequestBody @Valid BatchDTO batchDTO)throws BadRequestException {
		if (!batchRepository.existsById(batchDTO.getId())) {
			throw new RecordNotFoundException("Batch details doesn't exist ");
		}
		batchDTO = batchService.updateBatch(batchDTO);
		return new ResponseEntity<>(batchDTO, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteBatchById(@PathVariable(value = "id") int id) { 
		batchService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getBatchBySubCourseId")
	public ResponseEntity<List<BatchDTO>> getBatchListBySubCourseId(int subCourseId) {
		List<BatchDTO> batchDTOResponse = batchService.getBatchListBySubCourseId(subCourseId);
		return new ResponseEntity<>(batchDTOResponse, HttpStatus.OK);
	}
}
