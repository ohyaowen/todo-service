package com.services.todoservice.controller;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.entity.Task;
import com.services.todoservice.mapper.TaskMapper;
import com.services.todoservice.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {
    private TaskService taskService;
    // Handles API Request

    // Create Task
    @PostMapping("createTask")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO task){
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(TaskMapper.mapToTaskDTO(createdTask), HttpStatus.CREATED);
    }
    // Update Task
    @PutMapping("updateTask")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO task){
        Task updatedTask = taskService.updateTask(task);
        return new ResponseEntity<>(TaskMapper.mapToTaskDTO(updatedTask), HttpStatus.ACCEPTED);
    }

    // Delete Task
    @DeleteMapping("deleteTask")
    public ResponseEntity<String> deleteTask(@RequestBody TaskDTO task){
        taskService.deleteTask(task);
        return new ResponseEntity<>("Task Deleted!", HttpStatus.ACCEPTED);
    }
}
