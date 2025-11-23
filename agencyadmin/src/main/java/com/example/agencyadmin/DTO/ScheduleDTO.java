package com.example.agencyadmin.DTO;

import java.util.UUID;

public class ScheduleDTO {
    private UUID scheduleid;
    private String date;
    private String arrivaltime;
    private String departuretime;
    private String routeid;
    private String busid;
    private String agencyid;
    private String fairid;

    
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
    public String getRouteid() {
        return routeid;
    }
    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }
    public String getBusid() {
        return busid;
    }
    public void setBusid(String busid) {
        this.busid = busid;
    }
    public String getAgencyid() {
        return agencyid;
    }
    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
    public String getFairid() {
        return fairid;
    }
    public void setFairid(String fairid) {
        this.fairid = fairid;
    }
    

    
    
}
