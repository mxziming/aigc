package com.example.backend.newchat;

import lombok.Data;

import java.util.Date;
@Data
public class ChatHistory {
    private int uid;
    private int sessionid;
    private Date date;
}
