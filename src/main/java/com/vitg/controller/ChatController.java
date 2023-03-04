package com.vitg.controller;

import com.vitg.dto.ChatMessage;
import com.vitg.dto.ChatMessageV1;
import com.vitg.dto.ChatRoomDTO;
import com.vitg.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;
import java.util.Objects;
import java.util.Set;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry simpUserRegistry;

    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/addUserToRoom")
    @SendTo("/user/public")
    public ChatMessageV1 addUserToRoom(@Payload ChatMessageV1 chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        System.out.println(chatMessage);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUserName());
        return chatMessage;
    }


    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageV1 chatMessage) {
        ChatRoomDTO chatRoomDTO = chatRoomService.getChatRoomByUserId(chatMessage.getUserId());
         messagingTemplate.convertAndSendToUser(
                chatMessage.getUserId(),"/queue/messages",
                chatMessage
        );
    }

}



//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatMessageV1 sendMessage(@Payload ChatMessageV1 chatMessage) {
//        return chatMessage;
//    }
//
//    public Set<SimpUser> getUsers() {
//        return simpUserRegistry.getUsers();
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/addUser")
//    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        // Add username in web socket session
//        System.out.println("test");
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        return chatMessage;
//    }