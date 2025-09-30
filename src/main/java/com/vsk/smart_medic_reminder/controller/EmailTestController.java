package com.vsk.smart_medic_reminder.controller;



import com.vsk.smart_medic_reminder.service.ReminderSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class EmailTestController {

    private final ReminderSchedulerService schedulerService;

    @PostMapping("/send-reminders-now")
    public String sendRemindersNow() {
        schedulerService.sendPendingReminders();
        return "Reminder job triggered manually.";
    }
}

