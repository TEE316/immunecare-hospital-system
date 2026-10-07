package com.immunecare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Inventory Entity - Vaccine batch/lot management
 * FR12-FR15: Vaccine shipment management, stock decrement, expiry and low-stock alerts
 */
@Entity
@Table(name = "inventory", uniqueConstraints = {
    @UniqueConstraint(columnNames = "batch_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @Column(nullable = false, unique = true, length = 50)
    private String batchNumber;

    @Column(nullable = false)
    private Integer quantityReceived;

    @Column(nullable = false)
    private Integer quantityAvailable;

    @Column(nullable = false)
    private LocalDate manufactureDate;

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false, length = 100)
    private String supplierName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InventoryStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = InventoryStatus.ACTIVE;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        updateStatus();
    }

    private void updateStatus() {
        LocalDate today = LocalDate.now();
        if (expiryDate.isBefore(today)) {
            this.status = InventoryStatus.EXPIRED;
        } else if (expiryDate.minusDays(30).isBefore(today)) {
            this.status = InventoryStatus.EXPIRING_SOON;
        } else if (quantityAvailable < 10) {
            this.status = InventoryStatus.LOW_STOCK;
        } else {
            this.status = InventoryStatus.ACTIVE;
        }
    }
}
