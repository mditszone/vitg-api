package com.vitg.service;

import com.vitg.dto.ChatMessageV1;
import com.vitg.dto.ChatRoomDTO;
import com.vitg.entity.ChatRoom;

import java.io.IOException;
import java.util.List;

public interface ChatRoomService {
    List<ChatRoomDTO> getChatRoomBySupportTeamId(int userId);

    ChatRoomDTO getChatRoomByUserId(String userId);

    ChatRoom createChatRoom(ChatMessageV1 chatMessageV1) throws IOException;

    boolean getChatRoomOpenStatus(String userId);

}
