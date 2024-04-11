package com.services.todoservice.controller;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Task;
import com.services.todoservice.exception.TaskNotFoundException;
import com.services.todoservice.exception.UserNotFoundException;
import com.services.todoservice.mapper.TaskMapper;
import com.services.todoservice.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {
    private TaskService taskService;
    @Autowired
    private TaskMapper taskMapper;
    // Handles API Request
    // Create Task
    @PostMapping("createTask")
    public ResponseEntity<String> createTask(@RequestBody TaskDTO task){
        try{
            taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully.");
        }catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User: " + task.getUser().getUsername() + " does not exist.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    // Update Task
    @PutMapping("updateTask")
    public ResponseEntity<String> updateTask(@RequestBody TaskDTO task){
        try{
            taskService.updateTask(task);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task variables updated");
        }catch(TaskNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Delete Task
    @DeleteMapping("deleteTask")
    public ResponseEntity<String> deleteTask(@RequestBody TaskDTO task){
        try{
            taskService.deleteTask(task);
            return new ResponseEntity<>("Task Deleted!", HttpStatus.ACCEPTED);
        }catch(TaskNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get all Task
    @GetMapping("getAllTask")
    public ResponseEntity<?>getAllTask(@RequestBody UsersDTO user){
        try{
            List<Task> listOfTasks = taskService.getListOfTasks(user);
            if(listOfTasks.isEmpty()){
                throw new TaskNotFoundException("Task not found");
            }else{
                List<TaskDTO> listOfTaskDTO = new ArrayList<>();
                for(Task task : listOfTasks){
                    listOfTaskDTO.add(taskMapper.mapToTaskDTO(task));
                }
                return new ResponseEntity<>(listOfTasks, HttpStatus.ACCEPTED);
            }
        }catch(TaskNotFoundException e){
            return new ResponseEntity<>("Task not found!", HttpStatus.NOT_FOUND);
        }
    }
}
