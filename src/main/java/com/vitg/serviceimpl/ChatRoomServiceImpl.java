package com.vitg.serviceimpl;

import com.vitg.dto.ChatMessageV1;
import com.vitg.dto.ChatRoomDTO;
import com.vitg.entity.ChatRoom;
import com.vitg.repository.ChatRoomRepository;
import com.vitg.service.ChatRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ChatRoomDTO> getChatRoomBySupportTeamId(int supportTeamId) {

        return chatRoomRepository.findBySupportTeamId(supportTeamId)
                .stream()
                .map(item -> modelMapper.map(item, ChatRoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChatRoomDTO getChatRoomByUserId(String userId) {
        return modelMapper.map(chatRoomRepository.findByUserId(userId), ChatRoomDTO.class);
    }

    @Override
    public ChatRoom createChatRoom(ChatMessageV1 chatMessageV1) throws IOException {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setIsAccepted(true);
        chatRoom.setUserId(chatMessageV1.getUserId());
        chatRoom.setUserName(chatMessageV1.getUserName());
        chatRoom.setSupportTeamId(chatMessageV1.getSupportTeamId());
        chatRoomRepository.save(chatRoom);
//        File f = new File("./uploads/files/" + chatMessageV1.getUserId() + ".html");
//        if (f.createNewFile()) System.out.println("file already exists");
//        else System.out.println("new file created");
        return chatRoom;
    }

    @Override
    public boolean getChatRoomOpenStatus(String userId) {
        ChatRoom chatRoom = chatRoomRepository.findByUserId(userId);
        if (chatRoom.getIsAccepted()) return true;
        else return false;
    }


}
