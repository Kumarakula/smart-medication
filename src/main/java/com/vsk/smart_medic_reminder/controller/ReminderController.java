package com.vsk.smart_medic_reminder.controller;

import com.vsk.smart_medic_reminder.dto.ReminderDto;
import com.vsk.smart_medic_reminder.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    // Create a new reminder for a medicine
    @PostMapping
    public ResponseEntity<ReminderDto> createReminder(@RequestBody ReminderDto dto) {
        ReminderDto created = reminderService.createReminder(dto);
        return ResponseEntity.ok(created);
    }

    // Get all reminders for a specific medicine
    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<List<ReminderDto>> getRemindersForMedicine(@PathVariable Long medicineId) {
        List<ReminderDto> reminders = reminderService.getRemindersForMedicine(medicineId);
        return ResponseEntity.ok(reminders);
    }
}
