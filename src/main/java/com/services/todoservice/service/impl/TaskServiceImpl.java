package com.services.todoservice.service.impl;

import com.services.todoservice.dto.TaskDTO;
import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Task;
import com.services.todoservice.entity.Users;
import com.services.todoservice.exception.InvalidTaskException;
import com.services.todoservice.exception.TaskNotFoundException;
import com.services.todoservice.exception.UserNotFoundException;
import com.services.todoservice.mapper.TaskMapper;
import com.services.todoservice.repository.TasksRepository;
import com.services.todoservice.repository.UsersRepository;
import com.services.todoservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TaskMapper taskMapper;
    @Override
    @Transactional
    public Task createTask(TaskDTO taskDTO){
        // Convert DTO to JPA Entity and save the task to database
        Task task = TaskMapper.mapToTask(taskDTO);

        if(task.getTitle().isEmpty() || task.getDescription().isEmpty() || task.getDue_date().isEmpty()) {
            throw new InvalidTaskException("Invalid task input");
        }
        // Check if user ID exist
        Optional<Users> user = usersRepository.findById(task.getUser().getUser_id());
        if(user.isEmpty()){
            throw new UserNotFoundException("User not found.");
        }
        return tasksRepository.save(task);
    }

    @Override
    @Transactional
    public List<Task> getListOfTasks (UsersDTO user){
        return Optional.ofNullable(tasksRepository.findTaskByUserID(user.getUserID())).orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Task updateTask(TaskDTO taskDTO){
        // Since we allow for duplicated task, the task we are updating has to match entirely
        Task task = TaskMapper.mapToTask(taskDTO);
        // Check if task exist
        Optional.ofNullable(tasksRepository.getTask(task.getTask_id())).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        // Save the new properties
        return tasksRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(TaskDTO taskDTO) {
        Optional.ofNullable(tasksRepository.getTask(taskDTO.getId())).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        tasksRepository.deleteById(taskDTO.getId());
    }

}
