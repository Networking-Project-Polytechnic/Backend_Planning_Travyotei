package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "schedules")
public class Schedule {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scheduleid", updatable = false, nullable = false)
    private UUID scheduleid;
    @NotNull
    @Column(name = "date")  
    private String date;
    @NotNull
    @Column(name = "arrivaltime")
    private String arrivaltime;
    @NotNull
    @Column(name = "departuretime")
    private String departuretime;
    @NotNull
    @Column(name = "routeid")
    private String routeid;
    @NotNull
    @Column(name = "busid")
    private String busid;
    @NotNull
    @Column(name = "agencyid")
    private String agencyid;
    @NotNull
    @Column(name = "fairid")
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
