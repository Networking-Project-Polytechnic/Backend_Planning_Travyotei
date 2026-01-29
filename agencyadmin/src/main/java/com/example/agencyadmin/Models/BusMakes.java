package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "bus_make")
public class BusMakes {
    @Id
    @GeneratedValue
    @Column(name = "bus_make_id")
    private UUID busMakeId;

    @Column(name = "make_name", nullable = false)
    private String makeName;

    @Column(name = "agencyid")
    private String agencyid;

    public UUID getBusMakeId() {
        return busMakeId;
    }

    public void setBusMakeId(UUID busMakeId) {
        this.busMakeId = busMakeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
}
