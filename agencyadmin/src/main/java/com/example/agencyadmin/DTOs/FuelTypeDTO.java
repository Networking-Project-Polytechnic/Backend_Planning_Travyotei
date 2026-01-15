package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for FuelType entity.
 * This DTO is used to transfer fuel type information between layers of the application.
 * It encapsulates fuel type data (e.g., Petrol, Diesel, Electric, etc.).
 */
public class FuelTypeDTO {
    
    /** The unique identifier for the fuel type */
    private UUID fuelTypeId;
    
    /** The name of the fuel type (e.g., "Petrol", "Diesel", "Electric") */
    private String fuelTypeName;

    // ===== Constructors =====
    
    /**
     * Default constructor for FuelTypeDTO
     */
    public FuelTypeDTO() {
    }

    /**
     * Constructor with all fields
     */
    public FuelTypeDTO(UUID fuelTypeId, String fuelTypeName) {
        this.fuelTypeId = fuelTypeId;
        this.fuelTypeName = fuelTypeName;
    }

    // ===== Getters and Setters =====
    
    public UUID getFuelTypeId() { return fuelTypeId; }
    public void setFuelTypeId(UUID fuelTypeId) { this.fuelTypeId = fuelTypeId; }

    public String getFuelTypeName() { return fuelTypeName; }
    public void setFuelTypeName(String fuelTypeName) { this.fuelTypeName = fuelTypeName; }
}
