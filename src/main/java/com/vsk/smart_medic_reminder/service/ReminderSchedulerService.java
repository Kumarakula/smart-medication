package com.vsk.smart_medic_reminder.service;


import com.vsk.smart_medic_reminder.models.Reminder;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderSchedulerService {

    private final ReminderService reminderService;
    private final EmailService emailService;

    /**
     * Runs every 5 minutes to send email reminders for pending medicines.
     */
    @Scheduled(fixedRate = 300000) // 300,000 ms = 5 minutes
    public void sendPendingReminders() {
        LocalDateTime now = LocalDateTime.now();

        // Get all reminders that are PENDING and scheduled before or at 'now'
        List<Reminder> reminders = reminderService.findPendingRemindersBefore(now);

        for (Reminder reminder : reminders) {
            String userEmail = reminder.getMedicine().getUser().getEmail();
            String medicineName = reminder.getMedicine().getName();
            LocalDateTime reminderTime = reminder.getReminderTime();

            String subject = "Medicine Reminder: " + medicineName;
            String body = "This is a reminder to take your medicine \"" + medicineName
                    + "\" scheduled at " + reminderTime.toString();

            try {
                emailService.sendSimpleEmail(userEmail, subject, body);
                reminderService.markReminderAsSent(reminder);
                System.out.println("Sent reminder email to: " + userEmail);
            } catch (Exception e) {
                System.err.println("Failed to send email to " + userEmail + ": " + e.getMessage());
            }
        }
    }
}

