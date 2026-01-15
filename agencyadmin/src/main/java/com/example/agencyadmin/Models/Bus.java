package com.example.agencyadmin.Models;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bus_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID busId;

    @NotNull
    private UUID agencyId;
    @NotNull
    private UUID busMakeId;
    @NotNull
    private UUID busModelId;
    @NotNull
    private UUID manufacturerId;
    @NotNull
    private UUID fuelTypeId;
    @NotNull
    private UUID transmissionTypeId;
    @NotNull
    @Column(name = "bus_type_id")
    private UUID busTypeId;
    @NotNull
    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    private LocalDate registrationExpiryDate;
    private Integer totalSeats;
    private Float luggageCapacityKg;
    private Float tankCapacityLiters;
    private Float mileageKm;

    @OneToMany(mappedBy = "bus")
    private Set<BusReview> reviews;

    @ManyToMany
    @JoinTable(name = "bus_amenities", joinColumns = @JoinColumn(name = "bus_id"), inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<VehicleAmenity> amenities;

    @ManyToMany
    @JoinTable(name = "bus_transportables", joinColumns = @JoinColumn(name = "bus_id"), inverseJoinColumns = @JoinColumn(name = "transport_id"))
    private Set<BusCanTransport> canTransport;

    public UUID getBusId() {
        return busId;
    }

    public void setBusId(UUID busId) {
        this.busId = busId;
    }

    public UUID getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(UUID agencyId) {
        this.agencyId = agencyId;
    }

    public UUID getBusMakeId() {
        return busMakeId;
    }

    public void setBusMakeId(UUID busMakeId) {
        this.busMakeId = busMakeId;
    }

    public UUID getBusModelId() {
        return busModelId;
    }

    public void setBusModelId(UUID busModelId) {
        this.busModelId = busModelId;
    }

    public UUID getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(UUID manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public UUID getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(UUID fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public UUID getTransmissionTypeId() {
        return transmissionTypeId;
    }

    public void setTransmissionTypeId(UUID transmissionTypeId) {
        this.transmissionTypeId = transmissionTypeId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public LocalDate getRegistrationExpiryDate() {
        return registrationExpiryDate;
    }

    public void setRegistrationExpiryDate(LocalDate registrationExpiryDate) {
        this.registrationExpiryDate = registrationExpiryDate;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Float getLuggageCapacityKg() {
        return luggageCapacityKg;
    }

    public void setLuggageCapacityKg(Float luggageCapacityKg) {
        this.luggageCapacityKg = luggageCapacityKg;
    }

    public Float getTankCapacityLiters() {
        return tankCapacityLiters;
    }

    public void setTankCapacityLiters(Float tankCapacityLiters) {
        this.tankCapacityLiters = tankCapacityLiters;
    }

    public Float getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(Float mileageKm) {
        this.mileageKm = mileageKm;
    }

    public UUID getBusTypeId() {
        return busTypeId;
    }

    public void setBusTypeId(UUID busTypeId) {
        this.busTypeId = busTypeId;
    }

    public Set<BusReview> getReviews() {
        return reviews;
    }

    public void setReviews(Set<BusReview> reviews) {
        this.reviews = reviews;
    }

    public Set<VehicleAmenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<VehicleAmenity> amenities) {
        this.amenities = amenities;
    }

    public Set<BusCanTransport> getCanTransport() {
        return canTransport;
    }

    public void setCanTransport(Set<BusCanTransport> canTransport) {
        this.canTransport = canTransport;
    }
}