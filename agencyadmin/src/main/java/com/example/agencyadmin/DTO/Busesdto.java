package com.example.agencyadmin.DTO;

import java.util.UUID;

public class BusesDto {

    private UUID busid;
    private String busplatenumber;
    private int buscapacity;
    private String bustype;
    private String agencyid;

    public UUID getBusid() {
        return busid;
    }

    public void setBusid(UUID busid) {
        this.busid = busid;
    }

    public String getBusplatenumber() {
        return busplatenumber;
    }

    public void setBusplatenumber(String busplatenumber) {
        this.busplatenumber = busplatenumber;
    }

    public int getBuscapacity() {
        return buscapacity;
    }

    public void setBuscapacity(int buscapacity) {
        this.buscapacity = buscapacity;
    }

    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
}

