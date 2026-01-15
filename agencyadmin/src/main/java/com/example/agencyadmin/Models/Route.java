package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routeid", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID routeid;
    @NotNull
    @Column(name = "startlocationid", nullable = false)
    private UUID startlocationid;
    @NotNull
    @Column(name = "endlocationid", nullable = false)
    private UUID endlocationid;
    @NotNull
    @Column(name = "agencyid", nullable = false)
    private String agencyid;

    @jakarta.persistence.ElementCollection
    @jakarta.persistence.CollectionTable(name = "route_stop_points", joinColumns = @jakarta.persistence.JoinColumn(name = "route_id"))
    @jakarta.persistence.Column(name = "stop_point")
    private java.util.List<String> stopPoints;

    public UUID getRouteid() {
        return routeid;
    }

    public void setRouteid(UUID routeid) {
        this.routeid = routeid;
    }

    public UUID getStartlocationid() {
        return startlocationid;
    }

    public void setStartlocationid(UUID startlocationid) {
        this.startlocationid = startlocationid;
    }

    public UUID getEndlocationid() {
        return endlocationid;
    }

    public void setEndlocationid(UUID endlocationid) {
        this.endlocationid = endlocationid;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public java.util.List<String> getStopPoints() {
        return stopPoints;
    }

    public void setStopPoints(java.util.List<String> stopPoints) {
        this.stopPoints = stopPoints;
    }
}
