package com.services.todoservice.repository;

import com.services.todoservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Task, Integer> {
    // Spring Data JPA provides basic CRUD methods
}
