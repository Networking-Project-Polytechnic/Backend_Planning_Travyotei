package com.example.agencyadmin.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity(name = "bus_agency")
public class BusAgency {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "agency_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID agencyId;

@NotNull
@Column(name = "agency_name")
private String agencyName;

private String phone;
private String email;

@Column(name = "created_at")
private LocalDateTime createdAt;

public UUID getAgencyId() { return agencyId; }
public void setAgencyId(UUID agencyId) { this.agencyId = agencyId; }
public String getAgencyName() { return agencyName; }
public void setAgencyName(String agencyName) { this.agencyName = agencyName; }
public String getPhone() { return phone; }
public void setPhone(String phone) { this.phone = phone; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public LocalDateTime getCreatedAt() { return createdAt; }
public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}