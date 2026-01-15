package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for Location entity.
 * This DTO is used to transfer location information between layers of the application.
 * It encapsulates location-related data used in API requests/responses.
 */
public class LocationDTO {
    
    /** The unique identifier for the location */
    private UUID locationid;
    
    /** The name of the location (must be unique) */
    private String locationname;
    
    /** The agency ID associated with this location */
    private String agencyid;

    // ===== Constructors =====
    
    /**
     * Default constructor for LocationDTO
     */
    public LocationDTO() {
    }

    /**
     * Constructor with all fields
     */
    public LocationDTO(UUID locationid, String locationname, String agencyid) {
        this.locationid = locationid;
        this.locationname = locationname;
        this.agencyid = agencyid;
    }

    // ===== Getters and Setters =====
    
    public UUID getLocationid() { return locationid; }
    public void setLocationid(UUID locationid) { this.locationid = locationid; }

    public String getLocationname() { return locationname; }
    public void setLocationname(String locationname) { this.locationname = locationname; }

    public String getAgencyid() { return agencyid; }
    public void setAgencyid(String agencyid) { this.agencyid = agencyid; }
}
