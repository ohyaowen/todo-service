package com.services.todoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {
    private int id;
    private int userId;
    private String title;
    private String description;
    private String dueDate;
    private boolean completed;
}
