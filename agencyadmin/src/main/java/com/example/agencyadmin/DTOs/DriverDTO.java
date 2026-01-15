package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for Driver entity.
 * This DTO is used to transfer driver information between layers of the application.
 * It encapsulates driver-related data used in API requests/responses.
 */
public class DriverDTO {
    
    /** The unique identifier for the driver */
    private UUID driverId;
    
    /** The full name of the driver */
    private String fullName;
    
    /** Contact phone number of the driver */
    private String phone;
    
    /** The driver's license number (unique identifier) */
    private String licenseNumber;

    // ===== Constructors =====
    
    /**
     * Default constructor for DriverDTO
     */
    public DriverDTO() {
    }

    /**
     * Constructor with all fields
     */
    public DriverDTO(UUID driverId, String fullName, String phone, String licenseNumber) {
        this.driverId = driverId;
        this.fullName = fullName;
        this.phone = phone;
        this.licenseNumber = licenseNumber;
    }

    // ===== Getters and Setters =====
    
    public UUID getDriverId() { return driverId; }
    public void setDriverId(UUID driverId) { this.driverId = driverId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
}
