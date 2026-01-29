package com.example.agencyadmin.DTOs;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ScheduleDetailsDTO {
    private UUID scheduleid;
    private String date;
    private String arrivaltime;
    private String departuretime;
    private String agencyid;

    // Full Detailed Objects
    private BusDTO bus;
    private RouteDTO route;
    private RoutePriceDTO price;
    private DriverDTO driver;

    // Additional Related Info
    private List<BusImageDTO> busImages;
    private List<BusReviewDTO> busReviews;
    private List<DriverImageDTO> driverImages;
    private List<String> stopPoints;
    private LocationDTO startLocation;
    private LocationDTO endLocation;

    // Reference Data Names (for convenience)
    private String busTypeName;
    private String busMakeName;
    private String busModelName;

    public UUID getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(UUID scheduleid) {
        this.scheduleid = scheduleid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }

    public RoutePriceDTO getPrice() {
        return price;
    }

    public void setPrice(RoutePriceDTO price) {
        this.price = price;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
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

    public List<String> getStopPoints() {
        return stopPoints;
    }

    public void setStopPoints(List<String> stopPoints) {
        this.stopPoints = stopPoints;
    }

    public LocationDTO getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(LocationDTO startLocation) {
        this.startLocation = startLocation;
    }

    public LocationDTO getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(LocationDTO endLocation) {
        this.endLocation = endLocation;
    }

    public String getBusTypeName() {
        return busTypeName;
    }

    public void setBusTypeName(String busTypeName) {
        this.busTypeName = busTypeName;
    }

    public String getBusMakeName() {
        return busMakeName;
    }

    public void setBusMakeName(String busMakeName) {
        this.busMakeName = busMakeName;
    }

    public String getBusModelName() {
        return busModelName;
    }

    public void setBusModelName(String busModelName) {
        this.busModelName = busModelName;
    }
}
