package com.hatio.todoapplication.service;

import com.hatio.todoapplication.dto.UserDto;
import com.hatio.todoapplication.exceptions.user.UserAlreadyExistsException;
import com.hatio.todoapplication.exceptions.user.UserNotFoundException;
import com.hatio.todoapplication.model.AuthenticationResponse;
import com.hatio.todoapplication.model.User;
import com.hatio.todoapplication.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserDto request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }


        user= userRepository.save(user);

        String token = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse(token,user.getId(),user.getUsername());

        return response;
    }

    public AuthenticationResponse authenticate(User request){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(()->new UserNotFoundException("User not found with provided username"));
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token, user.getId(),user.getUsername());

    }
}
