package com.vsk.smart_medic_reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartMedicReminderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartMedicReminderApplication.class, args);
	}

}
