package com.services.todoservice.mapper;

import com.services.todoservice.dto.UsersDTO;
import com.services.todoservice.entity.Users;

public class UserMapper {
    // Convert JPA Entity to DTO
    public static UsersDTO mapToUsersDTO(Users user){
        UsersDTO UsersDTO = new UsersDTO(
            user.getUser_id(),
            user.getUser_name(),
            user.getUser_password()
        );
        return UsersDTO;
    }
    // Convert DTO to JPA Entity
    public static Users mapToUser (UsersDTO UsersDTO){
        return new Users(
            UsersDTO.getUserID(),
            UsersDTO.getUsername(),
            UsersDTO.getPassword()
        );
    }
}
