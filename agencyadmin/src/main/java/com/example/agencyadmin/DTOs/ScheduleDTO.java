package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for Schedule entity.
 * This DTO is used to transfer schedule information between layers of the
 * application.
 * It encapsulates schedule-related data including date, times, and references
 * to routes, buses, and fares.
 */
public class ScheduleDTO {

    /** The unique identifier for the schedule */
    private UUID scheduleid;

    /** The date of the schedule (as string, e.g., "2025-12-18") */
    private String date;

    /** The arrival time for this schedule (as string, e.g., "14:30") */
    private String arrivaltime;

    /** The departure time for this schedule (as string, e.g., "08:00") */
    private String departuretime;

    /** The route ID that this schedule operates on */
    private UUID routeid;

    /** The bus ID assigned to this schedule */
    private UUID busid;

    /** The agency ID that manages this schedule */
    private String agencyid;

    /** The route price ID associated with this schedule */
    private UUID priceid;

    /** The driver ID assigned to this schedule */
    private UUID driverid;

    // ===== Constructors =====

    /**
     * Default constructor for ScheduleDTO
     */
    public ScheduleDTO() {
    }

    /**
     * Constructor with all fields
     */
    public ScheduleDTO(UUID scheduleid, String date, String arrivaltime, String departuretime,
            UUID routeid, UUID busid, String agencyid, UUID priceid, UUID driverid) {
        this.scheduleid = scheduleid;
        this.date = date;
        this.arrivaltime = arrivaltime;
        this.departuretime = departuretime;
        this.routeid = routeid;
        this.busid = busid;
        this.agencyid = agencyid;
        this.priceid = priceid;
        this.driverid = driverid;
    }

    // ===== Getters and Setters =====

    public UUID getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(UUID scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public UUID getRouteid() {
        return routeid;
    }

    public void setRouteid(UUID routeid) {
        this.routeid = routeid;
    }

    public UUID getBusid() {
        return busid;
    }

    public void setBusid(UUID busid) {
        this.busid = busid;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public UUID getPriceid() {
        return priceid;
    }

    public void setPriceid(UUID priceid) {
        this.priceid = priceid;
    }

    public UUID getDriverid() {
        return driverid;
    }

    public void setDriverid(UUID driverid) {
        this.driverid = driverid;
    }
}
