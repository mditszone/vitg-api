package com.vitg.repository;

import com.vitg.dto.ChatRoomDTO;
import com.vitg.entity.ChatRoom;
import com.vitg.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    ChatRoom findByUserId(String userId);
    List<ChatRoom> findBySupportTeamId(int supportTeamId);

}
