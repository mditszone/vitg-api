package com.vitg;

import com.vitg.controller.BatchController;
import com.vitg.dto.BatchDTO;
import com.vitg.service.BatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class VitgApiApplicationTests {

	@InjectMocks
	private BatchController batchController;

	@Mock
	private BatchService batchService;


	private static BatchDTO batchDTO;
	private static List<BatchDTO> batchDTOList;

	@BeforeEach
	void setUp() {
		batchDTO = new BatchDTO();
		batchDTO.setId(1);
		batchDTOList = new ArrayList<>();
		batchDTOList.add(batchDTO);
	}

	@Test
	void createBatch() {
		doReturn(batchDTO).when(batchService).createBatch(batchDTO);
		ResponseEntity<BatchDTO> responseEntity = batchController.createBatch(batchDTO);
		assertNotNull(responseEntity);
	}

	@Test
	void getBatchById() {
		doReturn(batchDTO).when(batchService).getBatchById(1);
		ResponseEntity<BatchDTO> responseEntity = batchController.getBatchById(1);
		assertNotNull(responseEntity);
		assertEquals(1, Objects.requireNonNull(responseEntity.getBody()).getId());
	}

	@Test
	void getBatchAllBatches() {
		doReturn(batchDTOList).when(batchService).getAllBatches();
		List<BatchDTO>  responseEntity = batchController.getAllBatches();
		assertNotNull(responseEntity);
		assertEquals(1, responseEntity.get(0).getId());
		assertEquals(1, responseEntity.size());
	}

}
