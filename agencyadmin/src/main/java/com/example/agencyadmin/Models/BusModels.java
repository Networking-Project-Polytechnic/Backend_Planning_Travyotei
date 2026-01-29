package com.example.agencyadmin.Models;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "bus_model")
public class BusModels {
    @Id
    @GeneratedValue
    @Column(name = "bus_model_id")
    private UUID busModelId;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "agencyid")
    private String agencyid;

    public UUID getBusModelId() {
        return busModelId;
    }

    public void setBusModelId(UUID busModelId) {
        this.busModelId = busModelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
}
