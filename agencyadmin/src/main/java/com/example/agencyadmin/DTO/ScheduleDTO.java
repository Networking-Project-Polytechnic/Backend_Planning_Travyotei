package com.example.agencyadmin.DTO;

import java.util.UUID;

public class ScheduleDTO {
    private UUID scheduleid;
    private String date;
    private String arrivaltime;
    private String departuretime;
    private UUID routeid;
    private UUID busid;
    private String agencyid;
    private UUID fairid;

    
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
    public UUID getFairid() {
        return fairid;
    }
    public void setFairid(UUID fairid) {
        this.fairid = fairid;
    }
    

    
    
}
