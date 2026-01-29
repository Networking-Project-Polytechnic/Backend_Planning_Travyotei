package com.example.agencyadmin.DTOs;

import java.util.UUID;

/**
 * Data Transfer Object for RoutePrice entity.
 * This DTO is used to transfer route pricing information between layers of the
 * application.
 * It encapsulates price data for specific routes including validity periods and
 * currency.
 */
public class RoutePriceDTO {

    /** The unique identifier for the route price */
    private UUID priceId;

    /** The route ID for which this price applies */
    private UUID routeId;

    /** The bus ID associated with this pricing */
    private UUID busId;

    /** The price amount for this route */
    private Float priceAmount;

    /** The currency used for this price (e.g., "USD", "XAF") */
    private String currency;

    /** The agency ID associated with this price */
    private String agencyid;

    // ===== Constructors =====

    /**
     * Default constructor for RoutePriceDTO
     */
    public RoutePriceDTO() {
    }

    /**
     * Constructor with all fields
     */
    public RoutePriceDTO(UUID priceId, UUID routeId, UUID busId, String agencyid, Float priceAmount,
            String currency) {
        this.priceId = priceId;
        this.routeId = routeId;
        this.busId = busId;
        this.agencyid = agencyid;
        this.priceAmount = priceAmount;
        this.currency = currency;
    }

    // ===== Getters and Setters =====

    public UUID getPriceId() {
        return priceId;
    }

    public void setPriceId(UUID priceId) {
        this.priceId = priceId;
    }

    public UUID getRouteId() {
        return routeId;
    }

    public void setRouteId(UUID routeId) {
        this.routeId = routeId;
    }

    public UUID getBusId() {
        return busId;
    }

    public void setBusId(UUID busId) {
        this.busId = busId;
    }

    public Float getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Float priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
}
