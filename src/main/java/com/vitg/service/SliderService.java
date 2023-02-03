package com.vitg.service;

import java.util.List;

import com.vitg.dto.SliderDTO;

public interface SliderService {

	public SliderDTO getSliderById(int id);
	public void deleteById(int id);
	public SliderDTO addImage(SliderDTO sliderDTO);
	public List<SliderDTO> getAllSlider();
}
