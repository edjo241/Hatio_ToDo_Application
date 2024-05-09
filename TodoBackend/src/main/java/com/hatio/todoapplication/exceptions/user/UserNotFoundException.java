package com.hatio.todoapplication.exceptions.user;



public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
