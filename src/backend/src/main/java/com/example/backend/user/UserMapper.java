package com.example.backend.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insertUser(User user);

    User findUserByUsername(String username);
    void updateUser(User user);
}
