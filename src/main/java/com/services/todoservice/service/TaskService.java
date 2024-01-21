package com.services.todoservice.service;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.entity.Task;

public interface TaskService {
    // Business Logic
    Task createTask(TaskDTO task);
    Task updateTask(TaskDTO task);
    void deleteTask(TaskDTO task);
}
