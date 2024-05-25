package com.example.backend.chat;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.Date;
import java.util.List;

@Mapper
public interface ChatMapper {
    List<Integer> getChatHistory(int uid);
    List<Chat> getChats(int uid, int sessionid);
    void insertChat(Chat chat);
    int insertChatHistory(ChatHistory chatHistory);
}




