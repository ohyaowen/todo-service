package com.services.todoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int task_id;
    @ManyToOne(targetEntity = Users.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private int user_id;
    @Column(name = "title")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "due_date")
    private String due_date;
    @Column(name = "complete_status", nullable = false)
    private Boolean complete_status;

}
