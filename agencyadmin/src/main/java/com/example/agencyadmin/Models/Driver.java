package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "driver")
public class Driver {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "driver_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID driverId;

@NotNull
private String fullName;
private String phone;

@Column(unique = true)
private String licenseNumber;

public UUID getDriverId() {
    return driverId;
}

public void setDriverId(UUID driverId) {
    this.driverId = driverId;
}

public String getFullName() {
    return fullName;
}

public void setFullName(String fullName) {
    this.fullName = fullName;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public String getLicenseNumber() {
    return licenseNumber;
}

public void setLicenseNumber(String licenseNumber) {
    this.licenseNumber = licenseNumber;
}

// getters & setters
}