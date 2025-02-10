package com.example.demo.controller;

import com.example.demo.dto.UserProfileRequest;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping(params = "user_id")
    public User getUserById(@RequestParam Long user_id) {
        return userService.getUserById(user_id);
    }
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserProfileRequest request) {
        return userService.createUserWithProfile(request);
    }


}



