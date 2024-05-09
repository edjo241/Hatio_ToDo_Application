package com.hatio.todoapplication.controller;

import com.hatio.todoapplication.dto.UserDto;
import com.hatio.todoapplication.model.AuthenticationResponse;
import com.hatio.todoapplication.model.User;
import com.hatio.todoapplication.service.AuthenticationService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto request){
        ResponseEntity<AuthenticationResponse> authenticationResponse = ResponseEntity.ok(authService.register(request));
        return authenticationResponse;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request){
        return ResponseEntity.ok(authService.authenticate(request));
    }


}
