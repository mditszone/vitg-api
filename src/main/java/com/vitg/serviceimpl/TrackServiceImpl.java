
package com.vitg.serviceimpl;


import java.util.ArrayList;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitg.dto.TrackDTO;
import com.vitg.entity.Track;
import com.vitg.repository.BatchRepository;
import com.vitg.repository.TrackRepository;

import com.vitg.service.TrackService;

@Service
public class TrackServiceImpl implements TrackService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TrackRepository trackRepository;

	@Autowired
	private BatchRepository batchRepository;

	@Transactional
	public TrackDTO createTrack(TrackDTO trackDTO) {

		batchRepository.findById(trackDTO.getBatch().getId());
		Track track = modelMapper.map(trackDTO, Track.class);
		Track savedTrack = trackRepository.save(track);
		TrackDTO trackDTOResponse = modelMapper.map(savedTrack, TrackDTO.class);
		return trackDTOResponse;
	}

	public TrackDTO getTrackById(int id) {
		Track track=trackRepository.findById(id);
		TrackDTO trackDTO = modelMapper.map(track, TrackDTO.class);
		return trackDTO;

	}

	public List<TrackDTO> getAllTracks() {
		List<Track>  trackList = trackRepository.findAll();
		List<TrackDTO> trackDTOList = new ArrayList<>();
		for(Track track:trackList) {
			TrackDTO trackDTO=modelMapper.map(track, TrackDTO.class);
			trackDTOList.add(trackDTO);
		}
		return trackDTOList;
	}

	public TrackDTO updateTrack(TrackDTO trackDTO) {
		Track track = modelMapper.map(trackDTO, Track.class);
		Track trackResponse = trackRepository.save(track);
		TrackDTO trackDTOResponse = modelMapper.map(trackResponse, TrackDTO.class);
		return trackDTOResponse;

	}

	@Override
	public void deleteById(int id) {
		trackRepository.deleteById(id);
	}	


}
