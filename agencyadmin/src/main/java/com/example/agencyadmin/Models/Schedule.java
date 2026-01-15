package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scheduleid", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID scheduleid;
    @NotNull
    @Column(name = "date")
    private String date;
    @NotNull
    @Column(name = "arrivaltime")
    private String arrivaltime;
    @NotNull
    @Column(name = "departuretime")
    private String departuretime;
    @NotNull
    @Column(name = "routeid")
    private UUID routeid;
    @NotNull
    @Column(name = "busid")
    private UUID busid;
    @NotNull
    @Column(name = "agencyid")
    private String agencyid;
    @NotNull
    @Column(name = "priceid")
    private UUID priceid;

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

    public UUID getRouteid() {
        return routeid;
    }

    public void setRouteid(UUID routeid) {
        this.routeid = routeid;
    }

    public UUID getBusid() {
        return busid;
    }

    public void setBusid(UUID busid) {
        this.busid = busid;
    }

    public String getAgencyid() {
        return agencyid;
    }

    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    public UUID getPriceid() {
        return priceid;
    }

    public void setPriceid(UUID priceid) {
        this.priceid = priceid;
    }

}
