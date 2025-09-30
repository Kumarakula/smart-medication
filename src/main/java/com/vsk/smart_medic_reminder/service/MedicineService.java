package com.vsk.smart_medic_reminder.service;

import com.vsk.smart_medic_reminder.dto.MedicineDto;
import com.vsk.smart_medic_reminder.models.Medicine;
import com.vsk.smart_medic_reminder.models.User;
import com.vsk.smart_medic_reminder.repository.MedicineRepository;
import com.vsk.smart_medic_reminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    private final UserRepository userRepository;

    public MedicineDto createMedicine(MedicineDto dto, String email) {
        User user =userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Medicine medicine = Medicine.builder()
                .name(dto.getName())
                .dosage(dto.getDosage())
                .instructions(dto.getInstructions())
                .user(user)
                .build();

        Medicine saved = medicineRepository.save(medicine);
        return toDto(saved);
    }

    public List<MedicineDto> getMedicinesForUser(Optional<User> optionalUser) {
        User user = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        return medicineRepository.findByUser(user)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private MedicineDto toDto(Medicine medicine) {
        return MedicineDto.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .dosage(medicine.getDosage())
                .instructions(medicine.getInstructions())
                .build();
    }
}
