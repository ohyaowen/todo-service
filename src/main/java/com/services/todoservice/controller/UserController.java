package com.services.todoservice.controller;

import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Users;
import com.services.todoservice.exception.DuplicateUserException;
import com.services.todoservice.exception.UserNotFoundException;
import com.services.todoservice.mapper.UserMapper;
import com.services.todoservice.repository.UsersRepository;
import com.services.todoservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    @Autowired
    UsersRepository usersRepository;
    @PostMapping("createUser")
    public ResponseEntity<String> createUser (@RequestBody UsersDTO user){
        try{
            Users userCreated = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated.getUser_name() + " created successfully!");
        }catch(DuplicateUserException e){
            return ResponseEntity.status(HttpStatus.IM_USED).body("Username taken.");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user.");
        }


    }
    @DeleteMapping("deleteUser")
    public ResponseEntity<String> deleteUser (@RequestBody UsersDTO user){
        try{
            userService.deleteUser(user);
            return new ResponseEntity<>("Successfully deleted user: " + user.getUsername() + ".", HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
        }

    }

    @PutMapping("updateUser")
    public ResponseEntity<UsersDTO> updateUser (@RequestBody UsersDTO user){
        Users userUpdated = userService.updateUser(user);
        return new ResponseEntity<>(UserMapper.mapToUsersDTO(userUpdated), HttpStatus.ACCEPTED);
    }
}
