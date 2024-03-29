package com.services.todoservice.service.impl;

import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Users;
import com.services.todoservice.exception.DuplicateUserException;
import com.services.todoservice.exception.UserNotFoundException;
import com.services.todoservice.mapper.UserMapper;
import com.services.todoservice.repository.UsersRepository;
import com.services.todoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    @Autowired
    private UsersRepository usersRepository;
    @Transactional
    public Users createUser(UsersDTO UsersDTO){
        // Convert to JPA
        Users user = userMapper.mapToUser(UsersDTO);
        // Check if user exist
        Optional<Users> existingUser = Optional.ofNullable(usersRepository.findByuserName(user.getUser_name()));
        if(existingUser.isPresent()){
            throw new DuplicateUserException("User " + user.getUser_name() + " already exists.");
        }
        return usersRepository.save(user);
    }
    @Transactional
    public Users updateUser(UsersDTO usersDTO){
        // Convert to JPA
        Users updateUser = userMapper.mapToUser(usersDTO);
        Users user = Optional.ofNullable(usersRepository.findByuserName(updateUser.getUser_name()))
                .orElseThrow(() -> new UserNotFoundException("Username: " + updateUser.getUser_name() + " not found."));
        return usersRepository.save(user);
    }
    @Transactional
    public void deleteUser(UsersDTO usersDTO) {
        Users deleteUser = userMapper.mapToUser(usersDTO);
        Users user = Optional.ofNullable(usersRepository.findByuserName(deleteUser.getUser_name()))
                .orElseThrow(() -> new UserNotFoundException("Username: " + deleteUser.getUser_name() + " not found."));
        usersRepository.deleteById(user.getUser_id());
    }
}
