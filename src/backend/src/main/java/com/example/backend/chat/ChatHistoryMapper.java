package com.example.backend.chat;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatHistoryMapper {

    List<ChatHistory> getChatHistoriesByUid(int uid);
}



