package com.vsk.smart_medic_reminder.service;


import com.vsk.smart_medic_reminder.dto.UserRegisterDto;
import com.vsk.smart_medic_reminder.dto.UserResponseDto;
import com.vsk.smart_medic_reminder.models.User;
import com.vsk.smart_medic_reminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder


    public UserResponseDto registerUser(UserRegisterDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // Encode the password
                .role(User.Role.USER)
                .build();

        User savedUser = userRepository.save(user);
        return toResponseDto(savedUser);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
