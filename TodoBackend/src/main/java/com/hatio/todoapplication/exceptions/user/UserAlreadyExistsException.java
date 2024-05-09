package com.hatio.todoapplication.exceptions.user;

public class UserAlreadyExistsException  extends  RuntimeException{
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
