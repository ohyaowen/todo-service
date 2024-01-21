package com.services.todoservice.service.impl;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.entity.Task;
import com.services.todoservice.mapper.TaskMapper;
import com.services.todoservice.repository.TasksRepository;
import com.services.todoservice.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{
    private TasksRepository tasksRepository;
    private TaskMapper taskMapper;

    @Override
    public Task createTask(TaskDTO taskDTO){
        // Convert DTO to JPA Entity and save the task to database
        Task task = TaskMapper.mapToTask(taskDTO);
        if(tasksRepository.findById(task.getTask_id()).isPresent()){
            return null;
        }else{
            return task;
        }
    }

    @Override
    public Task updateTask(TaskDTO taskDTO){
        // Convert DTO to JPA Entity
        // Should find the task and hold it
        // Modify what you need to modify
        // Save it again
        Task task = TaskMapper.mapToTask(taskDTO);
        if(tasksRepository.findById(task.getTask_id()).isPresent()){
            return tasksRepository.save(task);
        }else{
            return null;
        }
    }

    @Override
    public void deleteTask(TaskDTO taskDTO){
        // Convert DTO to JPA Entity
        // Check if task exist
        // Delete it
        Task task = TaskMapper.mapToTask(taskDTO);
        if(tasksRepository.findById(task.getTask_id()).isPresent()){
            tasksRepository.deleteById(task.getTask_id());
        }
    }


}
