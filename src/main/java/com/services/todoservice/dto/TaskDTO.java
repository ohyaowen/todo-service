package com.services.todoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private String dueDate;
    private boolean completed;
}
