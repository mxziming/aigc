package com.example.backend.chat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    public String sendQusetionToAI(String question){




        return "success";
    }
//    @Autowired
//    private ChatMapper chatMapper;
//
//    @Autowired
//    private ChatHistoryMapper chatHistoryMapper;
//
//    public List<Chat> getChatsBySessionId(String sessionid) {
//        return chatMapper.getChatsBySessionId(sessionid);
//    }
//
//    public void insertChat(Chat chat) {
//        chatMapper.insertChat(chat);
//    }
//
//    public List<ChatHistory> getChatHistoriesByUid(int uid) {
//        return chatHistoryMapper.getChatHistoriesByUid(uid);
//    }
}
