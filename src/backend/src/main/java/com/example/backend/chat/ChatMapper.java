package com.example.backend.chat;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {


    List<Chat> getChatsBySessionId(String sessionid);

    void insertChat(Chat chat);
}




