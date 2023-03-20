package com.vitg.service;

import com.vitg.dto.FaqDataDTO;
import com.vitg.entity.FaqData;

import java.util.List;

public interface FaqDataService {
    FaqDataDTO createQuestion(FaqDataDTO faqDataDTO);
    List<FaqDataDTO> getAllFaqData();
}
