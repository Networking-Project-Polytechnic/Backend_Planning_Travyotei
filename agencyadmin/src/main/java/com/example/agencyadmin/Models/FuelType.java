package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity(name = "fuel_type")
public class FuelType {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "fuel_type_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID fuelTypeId;

@NotNull
@Column(name = "fuel_type_name", unique = true)
private String fuelTypeName;

public UUID getFuelTypeId() { return fuelTypeId; }
public void setFuelTypeId(UUID fuelTypeId) { this.fuelTypeId = fuelTypeId; }
public String getFuelTypeName() { return fuelTypeName; }
public void setFuelTypeName(String fuelTypeName) { this.fuelTypeName = fuelTypeName; }
}