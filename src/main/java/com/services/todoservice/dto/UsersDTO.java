package com.services.todoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsersDTO {
    Long userID;
    String username;
    String password;
}
