package com.immunecare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Vaccination Entity - Records of vaccination events
 * FR07: Recording vaccination events
 * FR10: Chronological immunization history
 */
@Entity
@Table(name = "vaccinations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vaccinationId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "administrator_id", nullable = false)
    private User administrator;

    @Column(nullable = false)
    private Integer doseNumber;

    @Column(nullable = false)
    private LocalDateTime dateAdministered;

    @Column(nullable = true)
    private LocalDate nextDueDate;

    @Column(nullable = false, unique = true, length = 50)
    private String certificateUuid;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
