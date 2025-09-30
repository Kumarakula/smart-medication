package com.vsk.smart_medic_reminder.service;


import com.vsk.smart_medic_reminder.dto.ReminderDto;
import com.vsk.smart_medic_reminder.models.Medicine;
import com.vsk.smart_medic_reminder.models.Reminder;
import com.vsk.smart_medic_reminder.repository.MedicineRepository;
import com.vsk.smart_medic_reminder.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final MedicineRepository medicineRepository;

    public ReminderDto createReminder(ReminderDto dto) {
        Medicine medicine = medicineRepository.findById(dto.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        Reminder reminder = Reminder.builder()
                .medicine(medicine)
                .reminderTime(dto.getReminderTime())
                .status(Reminder.Status.PENDING)
                .emailSent(false)
                .build();

        Reminder saved = reminderRepository.save(reminder);
        return toDto(saved);
    }

    public List<ReminderDto> getRemindersForMedicine(Long medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        return reminderRepository.findByMedicineOrderByReminderTimeAsc(medicine)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Reminder> findPendingRemindersBefore(LocalDateTime time) {
        return reminderRepository.findByStatusAndReminderTimeLessThanEqual(Reminder.Status.PENDING, time);
    }

    public void markReminderAsSent(Reminder reminder) {
        reminder.setStatus(Reminder.Status.SENT);
        reminder.setEmailSent(true);
        reminderRepository.save(reminder);
    }

    private ReminderDto toDto(Reminder reminder) {
        return ReminderDto.builder()
                .id(reminder.getId())
                .medicineId(reminder.getMedicine().getId())
                .reminderTime(reminder.getReminderTime())
                .status(reminder.getStatus().name())
                .emailSent(reminder.isEmailSent())
                .build();
    }
}

