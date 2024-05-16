package com.example.backend.task;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {

    List<Task> findByUserId(Integer userId);

    void insertTask(Task task);
    void updateTask(Task task);

    void deleteTask(Task task);
    Task findTaskById(Long taskId);
}
