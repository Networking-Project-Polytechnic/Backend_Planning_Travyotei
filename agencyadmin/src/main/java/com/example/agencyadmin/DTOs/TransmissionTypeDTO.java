package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for TransmissionType entity.
 * This DTO is used to transfer transmission type information between layers of
 * the application.
 * It encapsulates transmission type data (e.g., Manual, Automatic, CVT, etc.).
 */
public class TransmissionTypeDTO {

    /** The unique identifier for the transmission type */
    private UUID transmissionTypeId;

    /** The name of the transmission type (e.g., "Manual", "Automatic") */
    private String typeName;

    /** The agency ID associated with this transmission type */
    private String agencyId;

    // ===== Constructors =====

    /**
     * Default constructor for TransmissionTypeDTO
     */
    public TransmissionTypeDTO() {
    }

    /**
     * Constructor with all fields
     */
    public TransmissionTypeDTO(UUID transmissionTypeId, String typeName, String agencyId) {
        this.transmissionTypeId = transmissionTypeId;
        this.typeName = typeName;
        this.agencyId = agencyId;
    }

    // ===== Getters and Setters =====

    public UUID getTransmissionTypeId() {
        return transmissionTypeId;
    }

    public void setTransmissionTypeId(UUID transmissionTypeId) {
        this.transmissionTypeId = transmissionTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
