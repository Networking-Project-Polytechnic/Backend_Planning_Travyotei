package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "bus_manufacturer")
public class BusManufacturers {
    @Id
    @GeneratedValue
    @Column(name = "manufacturer_id")
    private UUID manufacturerId;

    @Column(name = "manufacturer_name", nullable = false)
    private String manufacturerName;

    @Column(name = "agencyid")
    private String agencyid;

    public UUID getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(UUID manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
}
