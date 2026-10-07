package com.immunecare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ImmuneCare Hospital Management System
 * Main Spring Boot Application Entry Point
 *
 * Features:
 * - FR01: User login with username and password
 * - FR02: Role-based access control (Admin, Healthcare Worker, Patient)
 * - FR03: Automatic logout after 30 minutes of inactivity
 * - FR04-FR18: Complete vaccination record management
 */
@SpringBootApplication
@EnableScheduling
public class ImmunecareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImmunecareApplication.class, args);
        System.out.println("\n" +
                "╔════════════════════════════════════════════════╗\n" +
                "║   ImmuneCare Hospital Management System v2.4   ║\n" +
                "║   Vaccination Record & Hospital Management     ║\n" +
                "║   Running on http://localhost:8080/api         ║\n" +
                "╚════════════════════════════════════════════════╝\n");
    }
}
