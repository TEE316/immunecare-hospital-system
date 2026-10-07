package com.immunecare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * AEFI Entity - Adverse Events Following Immunization
 * FR11: Recording AEFI linked to a vaccination event
 */
@Entity
@Table(name = "aefi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AEFI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aefiId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccination_id", nullable = false)
    private Vaccination vaccination;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reported_by_user_id", nullable = false)
    private User reportedByUser;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String reactionDetails;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AEFISeverity severity;

    @Column(name = "date_reported", nullable = false)
    private LocalDateTime dateReported;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FollowUpStatus followUpStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.dateReported == null) {
            this.dateReported = LocalDateTime.now();
        }
    }
}
