package com.example.agencyadmin.DTOs;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object for Route entity.
 * This DTO is used to transfer route information between layers of the
 * application.
 * It encapsulates route-related data including start location, end location,
 * and agency information.
 */
public class RouteDTO {

    /** The unique identifier for the route */
    private UUID routeid;

    /** The starting location ID for this route */
    private UUID startlocationid;

    /** The ending location ID for this route */
    private UUID endlocationid;

    /** The agency ID that operates this route */
    private String agencyid;

    /** Points where the buses can stop along this route */
    private List<String> stopPoints;

    // ===== Constructors =====

    /**
     * Default constructor for RouteDTO
     */
    public RouteDTO() {
    }

    /**
     * Constructor with all fields
     */
    public RouteDTO(UUID routeid, UUID startlocationid, UUID endlocationid, String agencyid) {
        this.routeid = routeid;
        this.startlocationid = startlocationid;
        this.endlocationid = endlocationid;
        this.agencyid = agencyid;
    }

    /**
     * Constructor with all fields including stop points
     */
    public RouteDTO(UUID routeid, UUID startlocationid, UUID endlocationid, String agencyid, List<String> stopPoints) {
        this.routeid = routeid;
        this.startlocationid = startlocationid;
        this.endlocationid = endlocationid;
        this.agencyid = agencyid;
        this.stopPoints = stopPoints;
    }

    // ===== Getters and Setters =====

    public UUID getRouteid() {
        return routeid;
    }

    public void setRouteid(UUID routeid) {
        this.routeid = routeid;
    }

    public UUID getStartlocationid() {
        return startlocationid;
    }

    public void setStartlocationid(UUID startlocationid) {
        this.startlocationid = startlocationid;
    }

    public UUID getEndlocationid() {
        return endlocationid;
    }

    public void setEndlocationid(UUID endlocationid) {
        this.endlocationid = endlocationid;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public List<String> getStopPoints() {
        return stopPoints;
    }

    public void setStopPoints(List<String> stopPoints) {
        this.stopPoints = stopPoints;
    }
}
