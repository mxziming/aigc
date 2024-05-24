package com.example.backend.chat;

import lombok.Data;

import java.util.Date;
@Data
public class ChatHistory {
    private int uid;
    private int sessionid;
    private Date date;
}
