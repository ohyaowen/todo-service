package com.services.todoservice.controller;

import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Users;
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
    public ResponseEntity<UsersDTO> createUser (@RequestBody UsersDTO user){
        Users userCreated = userService.createUser(user);
        // TODO: Handle if user is alr created
        return new ResponseEntity<>(UserMapper.mapToUsersDTO(userCreated), HttpStatus.CREATED);
    }
    @DeleteMapping("deleteUser")
    public ResponseEntity<String> deleteUser (@RequestBody UsersDTO user){
        userService.deleteUser(user);
        return new ResponseEntity<>("Successfully deleted user: " + user.getUsername() + ".", HttpStatus.ACCEPTED);
    }

    @PutMapping("updateUser")
    public ResponseEntity<UsersDTO> updateUser (@RequestBody UsersDTO user){
        Users userUpdated = userService.updateUser(user);
        return new ResponseEntity<>(UserMapper.mapToUsersDTO(userUpdated), HttpStatus.ACCEPTED);
    }
}
