package com.services.todoservice.service.impl;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.entity.Task;
import com.services.todoservice.exception.InvalidTaskException;
import com.services.todoservice.mapper.TaskMapper;
import com.services.todoservice.repository.TasksRepository;
import com.services.todoservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TasksRepository tasksRepository;
    private TaskMapper taskMapper;

    @Override
    @Transactional
    public Task createTask(TaskDTO taskDTO){
        // Convert DTO to JPA Entity and save the task to database
        Task task = TaskMapper.mapToTask(taskDTO);

        if (task.getTitle().isEmpty() || task.getDescription().isEmpty() || task.getDue_date().isEmpty()) {
            throw new InvalidTaskException("Invalid task input");
        }
        else {
            return tasksRepository.save(task);
        }
    }

    @Override
    @Transactional
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
    @Transactional
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
