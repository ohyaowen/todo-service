package com.services.todoservice.config;

import com.services.todoservice.mapper.TaskMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public TaskMapper taskMapper() {
        return new TaskMapper();
    }
}
