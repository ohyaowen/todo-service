package com.services.todoservice.repository;

import com.services.todoservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {
    // Spring Data JPA provides basic CRUD methods
    @Query(value = "SELECT u.user_id, u.user_name, u.user_password FROM users u WHERE u.user_name = :userName", nativeQuery = true)
    Users findByuserName(@Param("userName") String username);
}
