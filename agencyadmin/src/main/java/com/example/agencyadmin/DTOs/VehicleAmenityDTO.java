package com.example.agencyadmin.DTOs;

import java.util.UUID;

public class VehicleAmenityDTO {
    private UUID amenityId;
    private String amenityName;
    private String description;

    public UUID getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(UUID amenityId) {
        this.amenityId = amenityId;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String agencyId;

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
