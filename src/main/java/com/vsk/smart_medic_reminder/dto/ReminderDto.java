package com.vsk.smart_medic_reminder.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReminderDto {

    private Long id; // optional for updates

    @NotNull(message = "Medicine ID is required")
    private Long medicineId;

    @NotNull(message = "Reminder time is required")
    private LocalDateTime reminderTime;

    private String status; // Optional, default is PENDING

    private boolean emailSent;
}

