package com.services.todoservice.mapper;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.entity.Task;

public class TaskMapper {
    // Convert JPA Entity into DTO
    public static TaskDTO mapToTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO(
            task.getTask_id(),
            task.getUser_id(),
            task.getTitle(),
            task.getDescription(),
            task.getDue_date(),
            task.getComplete_status()
        );
        return taskDTO;
    }
    // Convert DTO to JPA Entity
    public static Task mapToTask(TaskDTO taskDTO){
        Task task = new Task(
            taskDTO.getId(),
            taskDTO.getUserId(),
            taskDTO.getTitle(),
            taskDTO.getDescription(),
            taskDTO.getDueDate(),
            taskDTO.isCompleted()
        );
        return task;
    }
}
