package com.immunecare.entity;

/**
 * User Role Enumeration
 * FR02: Role-based access control
 * Recommended role model per SRS:
 * - ADMINISTRATOR: User account and system management
 * - HEALTHCARE_WORKER: Patient and vaccination management
 * - PATIENT: Self-service access to own records
 */
public enum UserRole {
    ADMINISTRATOR,
    HEALTHCARE_WORKER,
    PATIENT
}
