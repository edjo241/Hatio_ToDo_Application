package com.hatio.todoapplication.exceptions.project;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
