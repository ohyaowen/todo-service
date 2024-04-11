package com.services.todoservice.service;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Task;

import java.util.List;

public interface TaskService {
    // Business Logic
    Task createTask(TaskDTO task);
    Task updateTask(TaskDTO task);
    List<Task>getListOfTasks(UsersDTO user);
    void deleteTask(TaskDTO task);
}
