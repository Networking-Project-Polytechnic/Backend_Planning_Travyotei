package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
@Entity(name = "transmission_type")
public class TransmissionType {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "transmission_type_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
private UUID transmissionTypeId;

@NotNull
@Column(name = "type_name", unique = true)
private String typeName;

public UUID getTransmissionTypeId() { return transmissionTypeId; }
public void setTransmissionTypeId(UUID transmissionTypeId) { this.transmissionTypeId = transmissionTypeId; }
public String getTypeName() { return typeName; }
public void setTypeName(String typeName) { this.typeName = typeName; }
}