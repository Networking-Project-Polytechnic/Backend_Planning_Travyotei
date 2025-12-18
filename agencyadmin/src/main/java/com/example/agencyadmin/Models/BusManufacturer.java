package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
// BusManufacturer.java
@Entity(name = "bus_manufacturer")
public class BusManufacturer {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "manufacturer_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID manufacturerId;

@NotNull
@Column(name = "manufacturer_name", unique = true)
private String manufacturerName;

public UUID getManufacturerId() { return manufacturerId; }
public void setManufacturerId(UUID manufacturerId) { this.manufacturerId = manufacturerId; }
public String getManufacturerName() { return manufacturerName; }
public void setManufacturerName(String manufacturerName) { this.manufacturerName = manufacturerName; 

}}