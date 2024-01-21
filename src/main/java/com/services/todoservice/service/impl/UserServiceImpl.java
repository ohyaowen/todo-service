package com.services.todoservice.service.impl;

import com.services.todoservice.dto.UserDTO;
import com.services.todoservice.entity.User;
import com.services.todoservice.mapper.UserMapper;
import com.services.todoservice.repository.UsersRepository;
import com.services.todoservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private UsersRepository usersRepository;
    public User createUser(UserDTO userDTO){
        // Convert to JPA
        User user = userMapper.mapToUser(userDTO);
        // Need to check if user exist
        if(usersRepository.findByuserName(user.getUserName()).isEmpty()){
            return null;
        }else {
            return usersRepository.save(user);
        }
    }
    public User updateUser(UserDTO userDTO){
        // Convert to JPA
        User user = userMapper.mapToUser(userDTO);
        // Need to check if user exist
        if(usersRepository.findByuserName(user.getUserName()).isEmpty()){
            return null;
        }else {
            return usersRepository.save(user);
        }
    }
    public void deleteUser(UserDTO userDTO){
        User user = userMapper.mapToUser(userDTO);
        if(!usersRepository.findByuserName(user.getUserName()).isEmpty()){
            usersRepository.delete(user);
        }
    }
}
