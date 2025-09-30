package com.vsk.smart_medic_reminder.repository;


import com.vsk.smart_medic_reminder.models.Medicine;
import com.vsk.smart_medic_reminder.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    // Find all medicines for a specific user
    List<Medicine> findByUser(User user);
}

