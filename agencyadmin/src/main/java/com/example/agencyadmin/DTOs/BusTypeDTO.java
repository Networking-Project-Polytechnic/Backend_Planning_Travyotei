package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for BusType entity.
 * This DTO is used to transfer bus type information between layers of the
 * application.
 * It encapsulates bus type data (e.g., Luxury, Standard, etc.).
 */
public class BusTypeDTO {

    /** The unique identifier for the bus type */
    private UUID busTypeId;

    /** The name of the bus type */
    private String busTypeName;

    /** The agency ID associated with this bus type */
    private String agencyId;

    // ===== Constructors =====

    /**
     * Default constructor for BusTypeDTO
     */
    public BusTypeDTO() {
    }

    /**
     * Constructor with all fields
     */
    public BusTypeDTO(UUID busTypeId, String busTypeName, String agencyId) {
        this.busTypeId = busTypeId;
        this.busTypeName = busTypeName;
        this.agencyId = agencyId;
    }

    // ===== Getters and Setters =====

    public UUID getBusTypeId() {
        return busTypeId;
    }

    public void setBusTypeId(UUID busTypeId) {
        this.busTypeId = busTypeId;
    }

    public String getBusTypeName() {
        return busTypeName;
    }

    public void setBusTypeName(String busTypeName) {
        this.busTypeName = busTypeName;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
