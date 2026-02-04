package com.example.agencyadmin.DTOs;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object for Bus entity.
 * This DTO is used to transfer bus data between layers of the application.
 * It encapsulates bus-related information and is typically used in API
 * requests/responses.
 */
public class BusDTO {

    /** The unique identifier for the bus */
    private UUID busId;

    /** Reference to the agency that owns this bus */
    private String agencyId;

    /** Reference to the bus make (manufacturer brand) */
    private UUID busMakeId;

    /** Reference to the bus model */
    private UUID busModelId;

    /** Reference to the manufacturer */
    private UUID manufacturerId;

    /** Reference to the fuel type used by this bus */
    private UUID fuelTypeId;

    /** Reference to the transmission type of this bus */
    private UUID transmissionTypeId;

    /** Reference to the bus type */
    private UUID busTypeId;

    /** The registration/license plate number of the bus */
    private String registrationNumber;

    /** The date when the vehicle registration expires */
    private LocalDate registrationExpiryDate;

    /** Total number of passenger seats in the bus */
    private Integer totalSeats;

    /** Luggage storage capacity in kilograms */
    private Float luggageCapacityKg;

    /** Fuel tank capacity in liters */
    private Float tankCapacityLiters;

    /** Current mileage of the bus in kilometers */
    private Float mileageKm;

    /** Reviews for this bus */
    private Set<BusReviewDTO> reviews;

    /** Amenities available on this bus */
    private Set<VehicleAmenityDTO> amenities;

    /** Items this bus can transport */
    private Set<BusCanTransportDTO> canTransport;

    // ===== Constructors =====

    /**
     * Default constructor for BusDTO
     */
    public BusDTO() {
    }

    /**
     * Constructor with all fields including new relations
     */
    public BusDTO(UUID busId, String agencyId, UUID busMakeId, UUID busModelId,
            UUID manufacturerId, UUID fuelTypeId, UUID transmissionTypeId, UUID busTypeId,
            String registrationNumber, LocalDate registrationExpiryDate,
            Integer totalSeats, Float luggageCapacityKg, Float tankCapacityLiters, Float mileageKm,
            Set<BusReviewDTO> reviews, Set<VehicleAmenityDTO> amenities, Set<BusCanTransportDTO> canTransport) {
        this.busId = busId;
        this.agencyId = agencyId;
        this.busMakeId = busMakeId;
        this.busModelId = busModelId;
        this.manufacturerId = manufacturerId;
        this.fuelTypeId = fuelTypeId;
        this.transmissionTypeId = transmissionTypeId;
        this.busTypeId = busTypeId;
        this.registrationNumber = registrationNumber;
        this.registrationExpiryDate = registrationExpiryDate;
        this.totalSeats = totalSeats;
        this.luggageCapacityKg = luggageCapacityKg;
        this.tankCapacityLiters = tankCapacityLiters;
        this.mileageKm = mileageKm;
        this.reviews = reviews;
        this.amenities = amenities;
        this.canTransport = canTransport;
    }

    // ===== Getters and Setters =====

    public UUID getBusId() {
        return busId;
    }

    public void setBusId(UUID busId) {
        this.busId = busId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
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

    public UUID getBusTypeId() {
        return busTypeId;
    }

    public void setBusTypeId(UUID busTypeId) {
        this.busTypeId = busTypeId;
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

    public Set<BusReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(Set<BusReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public Set<VehicleAmenityDTO> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<VehicleAmenityDTO> amenities) {
        this.amenities = amenities;
    }

    public Set<BusCanTransportDTO> getCanTransport() {
        return canTransport;
    }

    public void setCanTransport(Set<BusCanTransportDTO> canTransport) {
        this.canTransport = canTransport;
    }
}
