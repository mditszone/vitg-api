package com.vitg.controller;


import com.vitg.dto.BatchDTO;
import com.vitg.dto.ChatMessageV1;
import com.vitg.dto.ChatRoomOpenStatus;
import com.vitg.dto.ResponseDTO;
import com.vitg.entity.ChatRoom;
import com.vitg.service.ChatRoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatRoom")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createChatRoom")
    public ResponseEntity<ResponseDTO> createChatRoom(@RequestBody ChatMessageV1 chatMessageV1) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            ChatRoom chatRoom = chatRoomService.createChatRoom(chatMessageV1);
            responseDTO.setMessage("successfully created chatroom");
            responseDTO.setStatus(200);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setStatus(400);
            responseDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

    }

    @GetMapping("/getChatRoomOpenStatus/{userId}")
    public ResponseEntity<ResponseDTO> getChatRoomOpenStatus(@PathVariable("userId") String userId) {
            ResponseDTO responseDTO = new ResponseDTO();
        try {
            ChatRoomOpenStatus chatRoomOpenStatus = new ChatRoomOpenStatus();
            chatRoomOpenStatus.setChatStatus(chatRoomService.getChatRoomOpenStatus(userId));
            responseDTO.setData(chatRoomOpenStatus);
            responseDTO.setStatus(200);
            responseDTO.setMessage("Success");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setStatus(400);
            responseDTO.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }

    }


}
