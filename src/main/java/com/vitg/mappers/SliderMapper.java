package com.vitg.mappers;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vitg.dto.SliderDTO;
import com.vitg.entity.Slider;

@Component
public class SliderMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public SliderDTO sliderToSliderDTO(Slider slider) {
		return  modelMapper.map(slider , SliderDTO.class);
	}
}
