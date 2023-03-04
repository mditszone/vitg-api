package com.vitg.serviceimpl;

import com.vitg.dto.FaqDataDTO;
import com.vitg.entity.FaqData;
import com.vitg.repository.ChatRoomRepository;
import com.vitg.repository.FaqDataRepository;
import com.vitg.service.FaqDataService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FaqDataServiceImpl implements FaqDataService {

    @Autowired
    private FaqDataRepository faqDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FaqDataDTO createQuestion(FaqDataDTO faqDataDTO) {
        FaqData faqData = modelMapper.map(faqDataDTO, FaqData.class);
        faqData = faqDataRepository.save(faqData);
        return modelMapper.map(faqData, FaqDataDTO.class);
    }

    @Override
    public List<FaqDataDTO> getAllFaqData() {
        return faqDataRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, FaqDataDTO.class))
                .collect(Collectors.toList());
    }
}
