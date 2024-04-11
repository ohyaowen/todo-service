package com.services.todoservice.dto;

import com.services.todoservice.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private UsersDTO user;
    private String title;
    private String description;
    private String dueDate;
    private Boolean completed;
}
