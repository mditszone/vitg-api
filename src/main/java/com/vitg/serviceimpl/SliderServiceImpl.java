package com.vitg.serviceimpl;

import java.util.ArrayList;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitg.dto.SliderDTO;
import com.vitg.entity.Slider;
import com.vitg.mappers.SliderMapper;
import com.vitg.repository.SliderRepository;
import com.vitg.service.SliderService;

@Service
public class SliderServiceImpl implements SliderService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SliderRepository sliderRepository;
	
	@Autowired
	private SliderMapper sliderMapper;
	
	
	public List<SliderDTO> getAllSlider(){
		List<Slider>  sliderList=sliderRepository.findAll();
		List<SliderDTO> sliderDTOList = new ArrayList<>();
		for(Slider slider:sliderList) {
			SliderDTO sliderDTO=modelMapper.map(slider, SliderDTO.class);
			sliderDTOList.add(sliderDTO);
		}
		return sliderDTOList;
	}

	@Override
	public SliderDTO getSliderById(int id) {
		Slider slider = sliderRepository.findById(id);
		SliderDTO sliderDTO = sliderMapper.sliderToSliderDTO(slider);
		return sliderDTO;
	}
	


	@Override
	public void deleteById(int id) {
		sliderRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public SliderDTO addImage(SliderDTO sliderDTO) {
		Slider slider=(Slider) modelMapper.map(sliderDTO, Slider.class);

		Slider savedImage= sliderRepository.save(slider);
		SliderDTO sliderDTOResponse = modelMapper.map(savedImage, SliderDTO.class);
		return sliderDTOResponse;
	}
	
	
	
}
