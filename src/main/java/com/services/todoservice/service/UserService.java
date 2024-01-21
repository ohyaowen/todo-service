package com.services.todoservice.service;

import com.services.todoservice.dto.UserDTO;
import com.services.todoservice.entity.User;

public interface UserService {
    User updateUser(UserDTO user);
    void deleteUser(UserDTO user);
    User createUser(UserDTO user);
}
