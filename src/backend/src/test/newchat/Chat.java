package com.example.backend.newchat;

import lombok.Data;

import java.util.Date;
@Data
public class Chat {
    private int cid;
    private int uid;
    private int sessionid;
    private Date date;
    private String question;
    private String answer;
}
