package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "vehicle_amenity")
public class VehicleAmenity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "amenity_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID amenityId;

    @NotNull
    @Column(name = "amenity_name", unique = true)
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
}
