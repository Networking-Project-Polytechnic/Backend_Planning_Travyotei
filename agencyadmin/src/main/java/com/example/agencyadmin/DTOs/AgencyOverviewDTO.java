package com.example.agencyadmin.DTOs;

import java.util.List;

public class AgencyOverviewDTO {
    private String agencyId;

    // Agency Specific
    private List<BusDTO> buses;
    private List<DriverDTO> drivers;
    private List<RouteDTO> routes;
    private List<ScheduleDTO> schedules;
    private List<LocationDTO> locations;

    // Child Entities (Filtered by Agency parents)
    private List<BusImageDTO> busImages;
    private List<BusReviewDTO> busReviews;
    private List<DriverImageDTO> driverImages;
    private List<RoutePriceDTO> routePrices;

    // Global Reference Data
    private List<BusMakeDTO> busMakes;
    private List<BusManufacturerDTO> busManufacturers;
    private List<BusModelDTO> busModels;
    private List<BusTypeDTO> busTypes;
    private List<FuelTypeDTO> fuelTypes;
    private List<TransmissionTypeDTO> transmissionTypes;
    private List<VehicleAmenityDTO> vehicleAmenities;
    private List<BusCanTransportDTO> busCanTransport;

    // Default Constructor
    public AgencyOverviewDTO() {
    }

    // Getters and Setters
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public List<BusDTO> getBuses() {
        return buses;
    }

    public void setBuses(List<BusDTO> buses) {
        this.buses = buses;
    }

    public List<DriverDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverDTO> drivers) {
        this.drivers = drivers;
    }

    public List<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDTO> routes) {
        this.routes = routes;
    }

    public List<ScheduleDTO> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleDTO> schedules) {
        this.schedules = schedules;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }

    public List<BusImageDTO> getBusImages() {
        return busImages;
    }

    public void setBusImages(List<BusImageDTO> busImages) {
        this.busImages = busImages;
    }

    public List<BusReviewDTO> getBusReviews() {
        return busReviews;
    }

    public void setBusReviews(List<BusReviewDTO> busReviews) {
        this.busReviews = busReviews;
    }

    public List<DriverImageDTO> getDriverImages() {
        return driverImages;
    }

    public void setDriverImages(List<DriverImageDTO> driverImages) {
        this.driverImages = driverImages;
    }

    public List<RoutePriceDTO> getRoutePrices() {
        return routePrices;
    }

    public void setRoutePrices(List<RoutePriceDTO> routePrices) {
        this.routePrices = routePrices;
    }

    public List<BusMakeDTO> getBusMakes() {
        return busMakes;
    }

    public void setBusMakes(List<BusMakeDTO> busMakes) {
        this.busMakes = busMakes;
    }

    public List<BusManufacturerDTO> getBusManufacturers() {
        return busManufacturers;
    }

    public void setBusManufacturers(List<BusManufacturerDTO> busManufacturers) {
        this.busManufacturers = busManufacturers;
    }

    public List<BusModelDTO> getBusModels() {
        return busModels;
    }

    public void setBusModels(List<BusModelDTO> busModels) {
        this.busModels = busModels;
    }

    public List<BusTypeDTO> getBusTypes() {
        return busTypes;
    }

    public void setBusTypes(List<BusTypeDTO> busTypes) {
        this.busTypes = busTypes;
    }

    public List<FuelTypeDTO> getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(List<FuelTypeDTO> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public List<TransmissionTypeDTO> getTransmissionTypes() {
        return transmissionTypes;
    }

    public void setTransmissionTypes(List<TransmissionTypeDTO> transmissionTypes) {
        this.transmissionTypes = transmissionTypes;
    }

    public List<VehicleAmenityDTO> getVehicleAmenities() {
        return vehicleAmenities;
    }

    public void setVehicleAmenities(List<VehicleAmenityDTO> vehicleAmenities) {
        this.vehicleAmenities = vehicleAmenities;
    }

    public List<BusCanTransportDTO> getBusCanTransport() {
        return busCanTransport;
    }

    public void setBusCanTransport(List<BusCanTransportDTO> busCanTransport) {
        this.busCanTransport = busCanTransport;
    }
}
