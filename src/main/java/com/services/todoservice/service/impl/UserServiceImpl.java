package com.services.todoservice.service.impl;

import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Users;
import com.services.todoservice.exception.UserNotFoundException;
import com.services.todoservice.mapper.UserMapper;
import com.services.todoservice.repository.UsersRepository;
import com.services.todoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        // Need to check if user exist
        if(Optional.ofNullable(usersRepository.findByuserName(user.getUser_name())).isEmpty()){
            return null;
        }else {
            return usersRepository.save(user);
        }
    }
    @Transactional
    public Users updateUser(UsersDTO UsersDTO){
        // Convert to JPA
        Users user = userMapper.mapToUser(UsersDTO);
        // Need to check if user exist
        if(usersRepository.findByuserName(Optional.ofNullable(user.getUser_name()).orElse(null)) == null){
            return null;
        }else {

            return usersRepository.save(user);
        }
    }
    @Transactional
    public void deleteUser(UsersDTO usersDTO) {
        Users deleteUser = userMapper.mapToUser(usersDTO);
        Users user = Optional.ofNullable(usersRepository.findByuserName(deleteUser.getUser_name()))
                .orElseThrow(() -> new UserNotFoundException("Username: " + deleteUser.getUser_name() + " not found."));
        usersRepository.deleteById(user.getUser_id());
    }
}
