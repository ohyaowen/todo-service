package com.services.todoservice.repository;

import com.services.todoservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User,Integer> {
    // Spring Data JPA provides basic CRUD methods
    List<User>findByuserName(String username);
}
