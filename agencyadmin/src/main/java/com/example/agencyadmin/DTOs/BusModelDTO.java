package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for BusModels entity.
 * This DTO is used to transfer bus model information between layers of the application.
 * It encapsulates bus model data.
 */
public class BusModelDTO {
    
    /** The unique identifier for the bus model */
    private UUID busModelId;
    
    /** The name of the bus model */
    private String modelName;

    // ===== Constructors =====
    
    /**
     * Default constructor for BusModelDTO
     */
    public BusModelDTO() {
    }

    /**
     * Constructor with all fields
     */
    public BusModelDTO(UUID busModelId, String modelName) {
        this.busModelId = busModelId;
        this.modelName = modelName;
    }

    // ===== Getters and Setters =====
    
    public UUID getBusModelId() { return busModelId; }
    public void setBusModelId(UUID busModelId) { this.busModelId = busModelId; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
}

