package com.vsk.smart_medic_reminder.controller;

import com.vsk.smart_medic_reminder.dto.UserRegisterDto;
import com.vsk.smart_medic_reminder.dto.UserResponseDto;
import com.vsk.smart_medic_reminder.models.User;
import com.vsk.smart_medic_reminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRegisterDto dto) {
        UserResponseDto registered = userService.registerUser(dto);
        return ResponseEntity.ok(registered);
    }


    // Get currently logged-in user info
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(Principal principal) {
        String email = principal.getName(); // This will now be the authenticated user's email
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(userService.toResponseDto(user));
    }

    // Get all users (admin only in a real app)
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers()
                .stream()
                .map(userService::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }
}
