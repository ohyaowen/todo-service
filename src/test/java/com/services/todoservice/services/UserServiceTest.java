package com.services.todoservice.services;

import com.services.todoservice.entity.Users;
import com.services.todoservice.exception.DuplicateUserException;
import com.services.todoservice.exception.UserNotFoundException;
import com.services.todoservice.mapper.UserMapper;
import com.services.todoservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {
    @Autowired
    UserServiceImpl userService;

    @Test
    public void userCreationTest(){
        Users users = new Users();
        users.setUser_name("Test User 1");
        users.setUser_password("testpassword");
        users.setUser_id(1L);
        userService.createUser(UserMapper.mapToUsersDTO(users));
        Assertions.assertEquals(true,userService.userExist(users.getUser_name()));
    }

    @Test
    public void userDuplicateTest(){
        Users users = new Users();
        users.setUser_name("Test User 1");
        users.setUser_password("testpassword");
        users.setUser_id(1L);
        userService.createUser(UserMapper.mapToUsersDTO(users));
        // Duplication creation
        try{
            userService.createUser(UserMapper.mapToUsersDTO(users));
        }catch(DuplicateUserException e){
            Assertions.assertEquals("User Test User 1 already exists.", e.getMessage());
        }
    }

    @Test
    public void userDoNotExistTest(){
        Users users = new Users();
        users.setUser_name("Test User 1");
        users.setUser_password("testpassword");
        users.setUser_id(1L);
        try{
            userService.deleteUser(UserMapper.mapToUsersDTO(users));
        }catch(UserNotFoundException e){
            Assertions.assertEquals("Username: Test User 1 not found.",e.getMessage());
        }
    }

    @Test
    public void updateUserFailTest(){
        Users users = new Users();
        users.setUser_name("Test User 1");
        users.setUser_password("testpassword");
        users.setUser_id(1L);
        userService.createUser(UserMapper.mapToUsersDTO(users));
        // Only new password can be set, username can't be changed
        Users newUsers = new Users();
        newUsers.setUser_name("Test User 2");
        newUsers.setUser_password("testpassword2");
        newUsers.setUser_id(1L);
        try {
            userService.updateUser(UserMapper.mapToUsersDTO(newUsers));
        }catch(Exception e){
            Assertions.assertEquals("Username: Test User 2 not found.",e.getMessage());
        }
    }
    @Test
    public void updateUserSuccessTest(){
        Users users = new Users();
        users.setUser_name("Test User 1");
        users.setUser_password("testpassword");
        users.setUser_id(1L);
        userService.createUser(UserMapper.mapToUsersDTO(users));
        // Only new password can be set, username can't be changed
        Users newUsers = new Users();
        newUsers.setUser_name("Test User 1");
        newUsers.setUser_password("testpassword2");
        newUsers.setUser_id(1L);
        try {
            userService.updateUser(UserMapper.mapToUsersDTO(newUsers));
        }catch(Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);
    }

    @Test
    public void deleteUserSuccessTest(){
        Users users = new Users();
        users.setUser_name("Test User 1");
        users.setUser_password("testpassword");
        users.setUser_id(1L);
        userService.createUser(UserMapper.mapToUsersDTO(users));
        // Delete the same user
        userService.deleteUser(UserMapper.mapToUsersDTO(users));
        // User should no longer exist
        Assertions.assertEquals(false, userService.userExist(users.getUser_name()));
    }
    @Test
    public void deleteUserFailTest(){
        Users users = new Users();
        users.setUser_name("Test User 1");
        users.setUser_password("testpassword");
        users.setUser_id(1L);
        userService.createUser(UserMapper.mapToUsersDTO(users));
        // Delete the same user
        users.setUser_password("Test User 2");
        try{
            userService.deleteUser(UserMapper.mapToUsersDTO(users));
        }catch(Exception e){
            Assertions.assertEquals("Username: Test User 2 not found.",e.getMessage());
        }
    }
}
