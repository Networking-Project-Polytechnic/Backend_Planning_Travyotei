package com.example.agencyadmin.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "fair")
public class Fair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fairid", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID fairid;
    @NotNull
    @Column(name = "fairamount", unique = true)
    private float fairamount;    
    @NotNull
    @Column(name= "agencyid")
    private String agencyid;
    
    public UUID getFairid() {
        return fairid;
    }
    public void setFairid(UUID fairid) {
        this.fairid = fairid;
    }
    public float getFairamount() {
        return fairamount;
    }
    public void setFairamount(float fairamount) {
        this.fairamount = fairamount;
    }
    public String getAgencyid() {
        return agencyid;
    }
    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }
}
