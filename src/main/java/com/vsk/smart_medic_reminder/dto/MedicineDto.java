package com.vsk.smart_medic_reminder.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDto {

    private Long id; // for updates, can be null on creation

    @NotBlank(message = "Medicine name is required")
    private String name;

    @NotBlank(message = "Dosage is required")
    private String dosage;

    private String instructions;
}

