package com.vsk.smart_medic_reminder.controller;

import com.vsk.smart_medic_reminder.dto.MedicineDto;
import com.vsk.smart_medic_reminder.models.User;
import com.vsk.smart_medic_reminder.service.MedicineService;
import com.vsk.smart_medic_reminder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final UserService userService; // assuming you have a way to fetch user by email

    @PostMapping
    public ResponseEntity<MedicineDto> createMedicine(@RequestBody MedicineDto dto,@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email); // Replace with a default user or logic
        MedicineDto created = medicineService.createMedicine(dto, email);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<MedicineDto>> getUserMedicines(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        List<MedicineDto> medicines = medicineService.getMedicinesForUser(user);
        return ResponseEntity.ok(medicines);
    }

}
