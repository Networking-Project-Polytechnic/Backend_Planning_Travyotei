package com.example.agencyadmin.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity(name = "bus")
public class Bus {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "bus_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID busId;

@NotNull private UUID agencyId;
@NotNull private UUID busMakeId;
@NotNull private UUID busModelId;
@NotNull private UUID manufacturerId;
@NotNull private UUID fuelTypeId;
@NotNull private UUID transmissionTypeId;

@NotNull
@Column(name = "registration_number", unique = true)
private String registrationNumber;

private LocalDate registrationExpiryDate;
private Integer totalSeats;
private Float luggageCapacityKg;
private Float tankCapacityLiters;
private Float mileageKm;

private LocalDateTime createdAt;
private LocalDateTime updatedAt;

// getters & setters omitted for brevity (même structure que ci-dessus)
}