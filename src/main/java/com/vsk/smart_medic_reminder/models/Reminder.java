package com.vsk.smart_medic_reminder.models;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many reminders belong to one medicine
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private LocalDateTime reminderTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    private boolean emailSent;

    public enum Status {
        PENDING,
        SENT
    }
}

