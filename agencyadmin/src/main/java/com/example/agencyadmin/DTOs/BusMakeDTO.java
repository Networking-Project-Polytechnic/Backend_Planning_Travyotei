package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for BusMakes entity.
 * This DTO is used to transfer bus make information between layers of the application.
 * It encapsulates bus make data (e.g., Toyota, Mercedes, etc.).
 */
public class BusMakeDTO {
    
    /** The unique identifier for the bus make */
    private UUID busMakeId;
    
    /** The name of the bus make */
    private String makeName;

    // ===== Constructors =====
    
    /**
     * Default constructor for BusMakeDTO
     */
    public BusMakeDTO() {
    }

    /**
     * Constructor with all fields
     */
    public BusMakeDTO(UUID busMakeId, String makeName) {
        this.busMakeId = busMakeId;
        this.makeName = makeName;
    }

    // ===== Getters and Setters =====
    
    public UUID getBusMakeId() { return busMakeId; }
    public void setBusMakeId(UUID busMakeId) { this.busMakeId = busMakeId; }

    public String getMakeName() { return makeName; }
    public void setMakeName(String makeName) { this.makeName = makeName; }
}

