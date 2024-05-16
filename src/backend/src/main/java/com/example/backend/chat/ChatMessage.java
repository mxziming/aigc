package com.example.backend.chat;

import lombok.Data;

import java.util.Map;

@Data
public class ChatMessage {
    private String content;
    private String sessionid;
    private Map<String, String> queryParams;
}
