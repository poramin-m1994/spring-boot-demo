package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
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
    public ResponseEntity<?>  getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            ApiResponse<?> response = new ApiResponse<>(
            false,
            e.getMessage(),
            400,
            null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
    @GetMapping(params = "user_id")
    public ResponseEntity<?> getUserById( Long user_id) {
        try {
            if (user_id == null) {
            throw new IllegalArgumentException("User ID is required");
            }
            return userService.getUserById(user_id);
        } catch (Exception e) {
            ApiResponse<?> response = new ApiResponse<>(
            false,
            e.getMessage(),
            400,
            null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserProfileRequest request) {
        return userService.createUserWithProfile(request);
    }


}



