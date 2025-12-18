package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity(name = "bus_make")
public class BusMake {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "bus_make_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID busMakeId;

@NotNull
@Column(name = "make_name", unique = true)
private String makeName;

public UUID getBusMakeId() { return busMakeId; }
public void setBusMakeId(UUID busMakeId) { this.busMakeId = busMakeId; }
public String getMakeName() { return makeName; }
public void setMakeName(String makeName) { this.makeName = makeName; }
}