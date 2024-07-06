package com.services.todoservice.services;

import com.services.todoservice.entity.Task;
import com.services.todoservice.entity.Users;
import com.services.todoservice.exception.UserNotFoundException;
import com.services.todoservice.mapper.TaskMapper;
import com.services.todoservice.mapper.UserMapper;
import com.services.todoservice.service.impl.TaskServiceImpl;
import com.services.todoservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TaskServiceTest {
    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void createTaskWithoutUserTest(){
        // Create a user, but do not store the user to DB for testing
        Users newUser = new Users();
        newUser.setUser_id(1L);
        newUser.setUser_name("Test User");
        newUser.setUser_password("testpassword");
        // Creating a task
        Task newTask = new Task();
        newTask.setTask_id(1L);
        newTask.setUser(
                newUser
        );
        newTask.setTitle("Test Task");
        newTask.setDescription("Test Task Description");
        newTask.setDue_date("2024-04-20");
        newTask.setComplete_status(false);
        // Since the user does not exist, it will throw user not found exception.
        try{
            taskService.createTask(TaskMapper.mapToTaskDTO(newTask));
        }catch(UserNotFoundException e){
            Assertions.assertEquals("User not found.", e.getMessage());
        }
        // No task should be fetched.
        List<Task> fetchedTask = taskService.getListOfTasks(UserMapper.mapToUsersDTO(newUser));
        Assertions.assertEquals(0,fetchedTask.size());
    }

    @Test
    public void createUserTaskTest(){
        Task newTask = new Task();
        Users newUser = new Users();
        // Creating the test user
        newUser.setUser_id(1L);
        newUser.setUser_name("Test User");
        newUser.setUser_password("testpassword");
        userService.createUser(UserMapper.mapToUsersDTO(newUser));
        Assertions.assertEquals(true,userService.userExist("Test User"));
        // Creating the new task
        newTask.setTask_id(1L);
        newTask.setUser(
            newUser
        );
        newTask.setTitle("Test Task");
        newTask.setDescription("Test Task Description");
        newTask.setDue_date("2024-04-20");
        newTask.setComplete_status(false);
        taskService.createTask(TaskMapper.mapToTaskDTO(newTask));
        // Fetching the new Task, expecting 1 result
        List<Task> fetchedTask = taskService.getListOfTasks(UserMapper.mapToUsersDTO(newUser));
        Assertions.assertEquals(1, fetchedTask.size());
    }
}

