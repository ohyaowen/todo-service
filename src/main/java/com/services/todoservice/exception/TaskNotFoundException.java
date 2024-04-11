package com.services.todoservice.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message){
        super(message);
    }
    TaskNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
