package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for BusManufacturers entity.
 * This DTO is used to transfer bus manufacturer information between layers of
 * the application.
 * It encapsulates bus manufacturer data.
 */
public class BusManufacturerDTO {

    /** The unique identifier for the bus manufacturer */
    private UUID manufacturerId;

    /** The name of the bus manufacturer */
    private String manufacturerName;

    /** The agency ID associated with this bus manufacturer */
    private String agencyId;

    // ===== Constructors =====

    /**
     * Default constructor for BusManufacturerDTO
     */
    public BusManufacturerDTO() {
    }

    /**
     * Constructor with all fields
     */
    public BusManufacturerDTO(UUID manufacturerId, String manufacturerName, String agencyId) {
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.agencyId = agencyId;
    }

    // ===== Getters and Setters =====

    public UUID getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(UUID manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
