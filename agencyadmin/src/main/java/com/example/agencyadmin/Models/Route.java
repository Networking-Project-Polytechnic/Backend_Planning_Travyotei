package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "routes")
public class Route {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "routeid", updatable = false, nullable = false)
    private UUID routeid;
    @NotNull 
    @Column(name = "startlocationid", nullable = false)
    private String startlocationid;
    @NotNull 
    @Column(name = "endlocationid", nullable = false)
    private String endlocationid;
    @NotNull 
    @Column(name = "agencyid", nullable = false)
    private String agencyid;

    
    public UUID getRouteid() {
        return routeid;
    }
    public void setRouteid(UUID routeid) {
        this.routeid = routeid;
    }
    public String getStartlocationid() {
        return startlocationid;
    }
    public void setStartlocationid(String startlocationid) {
        this.startlocationid = startlocationid;
    }
    public String getEndlocationid() {
        return endlocationid;
    }
    public void setEndlocationid(String endlocationid) {
        this.endlocationid = endlocationid;
    }
    public String getAgencyid() {
        return agencyid;
    }
    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    
}

