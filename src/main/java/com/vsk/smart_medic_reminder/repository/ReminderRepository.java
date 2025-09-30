package com.vsk.smart_medic_reminder.repository;


import com.vsk.smart_medic_reminder.models.Medicine;
import com.vsk.smart_medic_reminder.models.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    // Find reminders for a medicine ordered by time
    List<Reminder> findByMedicineOrderByReminderTimeAsc(Medicine medicine);

    // Find all reminders with PENDING status and reminderTime before or equal to now (for scheduled task)
    List<Reminder> findByStatusAndReminderTimeLessThanEqual(Reminder.Status status, LocalDateTime time);
}

