package com.hatio.todoapplication.model;



public class AuthenticationResponse {

    private String token;
    private Integer userId;
    private String userName;

    public AuthenticationResponse(String token, Integer userId, String userName) {
        this.token = token;
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }

    public Integer getUserId() {
        return userId;
    }
}
