package com.example.demo.service;

import com.example.demo.dto.UserProfileRequest;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // เพิ่มเมธอดสำหรับสร้าง User และ Profile พร้อมกัน
    public UserResponseDto createUserWithProfile(UserProfileRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEnabled(request.isEnabled());
    
        Profile profile = new Profile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setUser(user);
    
        // ตั้งค่าความสัมพันธ์ระหว่าง User กับ Profile
        user.setProfile(profile);
    
        // บันทึกข้อมูลในฐานข้อมูล
        user = userRepository.save(user);
    
        // แปลงจาก User เป็น UserResponseDto
        return mapToUserResponseDto(user);
    }
    
    private UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEnabled(user.isEnabled());
    
        UserResponseDto.UserProfileResponseDto profileDto = new UserResponseDto.UserProfileResponseDto();
        profileDto.setId(user.getProfile().getId());
        profileDto.setFirstName(user.getProfile().getFirstName());
        profileDto.setLastName(user.getProfile().getLastName());
        profileDto.setEmail(user.getProfile().getEmail());
        profileDto.setPhoneNumber(user.getProfile().getPhoneNumber());
    
        dto.setProfile(profileDto);
        return dto;
    }
    
}
