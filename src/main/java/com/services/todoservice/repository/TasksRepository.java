package com.services.todoservice.repository;

import com.services.todoservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Long> {
    // Spring Data JPA provides basic CRUD methods
    @Query(value = "SELECT task FROM tasks task WHERE user_id = userID", nativeQuery = true)
    List<Task> listOfTasks(@Param("userID") String userID);
}
