package com.hatio.todoapplication.exceptions.todo;


public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String message){
        super(message);
    }
}
