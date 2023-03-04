package com.vitg.dto;

import lombok.Data;

@Data
public class ChatRoomDTO {

    private int id;
    private String userId;

    private int supportTeamId;

    private Boolean isAccepted;

    private String status;

    private String chatFilePath;

}
