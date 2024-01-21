package com.services.todoservice.mapper;

import com.services.todoservice.dto.UserDTO;
import com.services.todoservice.entity.User;

public class UserMapper {
    // Convert JPA Entity to DTO
    public UserDTO mapToUserDTO (User user){
        UserDTO userDTO = new UserDTO(
            user.getUserID(),
            user.getUserName()
        );
        return userDTO;
    }
    // Convert DTO to JPA Entity
    public User mapToUser (UserDTO userDTO){
        return new User(
            userDTO.getUserID(),
            userDTO.getUsername()
        );
    }
}
