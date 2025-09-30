package com.vsk.smart_medic_reminder.controller;


import com.vsk.smart_medic_reminder.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test-email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    /**
     * Sends a test email to the specified address.
     *
     * Example: GET /api/test-email/send?to=someone@example.com
     */
    @GetMapping("/send")
    public ResponseEntity<String> sendTestEmail(@RequestParam String to) {
        String subject = "Test Email from Smart Medicine Reminder App";
        String body = "This is a test email to verify email sending functionality.";

        try {
            emailService.sendSimpleEmail(to, subject, body);
            return ResponseEntity.ok("Test email sent successfully to " + to);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        }
    }
}

