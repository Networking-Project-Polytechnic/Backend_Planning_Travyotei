package com.example.agencyadmin.Models;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "buses")
public class Buses {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "busid", updatable = false, nullable = false)
    private UUID busid;

    @NotNull
    @Column(name = "busplatenumber", unique = true)
    private String busplatenumber;

    @NotNull
    @Column(name = "buscapacity")
    private int buscapacity;

    @NotNull
    @Column(name = "bustype")
    private String bustype; 
    
    @NotNull
    @Column(name= "agencyid")
    private String agencyid;

    @NotNull
    @Column(name = "trackingid", unique = true)
    private String trackingid;


    

    public UUID getBusid() {
        return busid;
    }

    public void setBusid(UUID busid) {
        this.busid = busid;
    }

    public String getBusplatenumber() {
        return busplatenumber;
    }

    public void setBusplatenumber(String busplatenumber) {
        this.busplatenumber = busplatenumber;
    }

    public int getBuscapacity() {
        return buscapacity;
    }

    public void setBuscapacity(int buscapacity) {
        this.buscapacity = buscapacity;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public String getTrackingid() {
        return trackingid;
    }

    public void setTrackingid(String trackingid) {
        this.trackingid = trackingid;
    }

    
    

   
}
