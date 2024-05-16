package com.example.backend.chat;

import org.apache.ibatis.annotations.Mapper;

//@Repository
@Mapper
public interface ChatMapper {
    void save(ChatMessage message);
}
