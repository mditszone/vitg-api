package com.vitg.dto;

import lombok.Data;

import java.util.Date;


@Data
public class ChatMessageV1 {
    private String id;
    private String chatId;
    private String userId;
    private int supportTeamId;
    private String supportTeamName;
    private String userName;
    private String content;
    private Date timestamp;
    private boolean sender;

    MessageStatus messageStatus;
    private enum MessageStatus {
        RECEIVED, DELIVERED
    }
}


