package com.example.agencyadmin.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity(name = "bus_model")
public class BusModel {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "bus_model_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID busModelId;

@NotNull
@Column(name = "bus_make_id")
private UUID busMakeId;

@NotNull
@Column(name = "model_name")
private String modelName;

@Column(name = "created_at")
private LocalDateTime createdAt;

public UUID getBusModelId() { return busModelId; }
public void setBusModelId(UUID busModelId) { this.busModelId = busModelId; }
public UUID getBusMakeId() { return busMakeId; }
public void setBusMakeId(UUID busMakeId) { this.busMakeId = busMakeId; }
public String getModelName() { return modelName; }
public void setModelName(String modelName) { this.modelName = modelName; }
public LocalDateTime getCreatedAt() { return createdAt; }
public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}