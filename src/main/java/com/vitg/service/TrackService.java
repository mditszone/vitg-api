
package com.vitg.service;

import com.vitg.dto.TrackDTO;

import java.util.List;

public interface TrackService {
	public TrackDTO getTrackById(int id);
	public void deleteById(int id);
	public TrackDTO createTrack(TrackDTO trackDTO);
	public TrackDTO updateTrack(TrackDTO trackDTO);
	public List<TrackDTO> getAllTracks();
}
