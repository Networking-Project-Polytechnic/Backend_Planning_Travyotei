package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "locationid", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID locationid;
    @NotNull
    @Column(name = "locationname", unique = true)
    private String locationname;  
    @NotNull
    @Column(name= "agencyid")
    private String agencyid;
    
    public UUID getLocationid() {
        return locationid;
    }
    public void setLocationid(UUID locationid) {
        this.locationid = locationid;
    }
    public String getLocationname() {
        return locationname;
    }
    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }
    public String getAgencyid() {
        return agencyid;
    }
    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }


    
}

//generate id based on name of agency etc example Amourmezam_Bamenda@3312