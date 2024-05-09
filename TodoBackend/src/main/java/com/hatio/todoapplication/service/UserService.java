package com.hatio.todoapplication.service;

import com.hatio.todoapplication.exceptions.user.UserNotFoundException;
import com.hatio.todoapplication.model.User;
import com.hatio.todoapplication.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<User> getUserByIdService(Integer id) {
        User fetchedUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with the corresponding id not found"));
        return ResponseEntity.ok(fetchedUser);
    }
}
