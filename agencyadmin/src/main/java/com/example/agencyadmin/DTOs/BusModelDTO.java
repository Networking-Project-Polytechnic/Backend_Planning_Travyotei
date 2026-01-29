package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for BusModels entity.
 * This DTO is used to transfer bus model information between layers of the
 * application.
 * It encapsulates bus model data.
 */
public class BusModelDTO {

    /** The unique identifier for the bus model */
    private UUID busModelId;

    /** The name of the bus model */
    private String modelName;

    /** The agency ID associated with this bus model */
    private String agencyId;

    // ===== Constructors =====

    /**
     * Default constructor for BusModelDTO
     */
    public BusModelDTO() {
    }

    /**
     * Constructor with all fields
     */
    public BusModelDTO(UUID busModelId, String modelName, String agencyId) {
        this.busModelId = busModelId;
        this.modelName = modelName;
        this.agencyId = agencyId;
    }

    // ===== Getters and Setters =====

    public UUID getBusModelId() {
        return busModelId;
    }

    public void setBusModelId(UUID busModelId) {
        this.busModelId = busModelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
