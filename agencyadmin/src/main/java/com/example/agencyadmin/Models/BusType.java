package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "bus_type")
public class BusType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bus_type_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID busTypeId;

    @NotNull
    @Column(name = "bus_type_name")
    private String busTypeName;

    @Column(name = "agencyid")
    private String agencyid;

    public UUID getBusTypeId() {
        return busTypeId;
    }

    public void setBusTypeId(UUID busTypeId) {
        this.busTypeId = busTypeId;
    }

    public String getBusTypeName() {
        return busTypeName;
    }

    public void setBusTypeName(String busTypeName) {
        this.busTypeName = busTypeName;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
}
