package com.services.todoservice.repository;

import com.services.todoservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Long> {
    // Spring Data JPA provides basic CRUD method
    @Query(value = "SELECT * FROM tasks WHERE user_id = :userID ", nativeQuery = true)
    List<Task> findTaskByUserID(@Param("userID")Long userID);
    @Query(value = "SELECT * FROM tasks WHERE task_id = :taskID", nativeQuery = true)
    Task getTask(@Param("taskID") Long taskid);
}
