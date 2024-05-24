package com.example.backend.newchat;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {


    List<Chat> getChatsBySessionId(String sessionid);

    void insertChat(Chat chat);
}




