
package com.vitg.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vitg.entity.Track;
@Repository
public interface TrackRepository extends JpaRepository<Track, Integer>{
	Track findById(int id);	
	
	@Query(value ="SELECT * FROM vitgdb.track" ,nativeQuery = true)
	List<Track> getTracksList();

}

