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
@Entity(name = "route_price")
public class RoutePrice {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "price_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID priceId;

@NotNull private UUID routeId;
@NotNull private UUID busId;

@NotNull
private Float priceAmount;

private String currency;
private LocalDate validFrom;
private LocalDate validTo;

// getters & setters
}
