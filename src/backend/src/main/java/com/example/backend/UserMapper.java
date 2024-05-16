package com.example.backend;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User findUserByUsername(String username);
    void updateUser(User user);
}
