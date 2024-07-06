package com.services.todoservice.service;

import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Users updateUser(UsersDTO user);
    void deleteUser(UsersDTO user);
    Users createUser(UsersDTO user);
    Boolean userExist(String username);
}
