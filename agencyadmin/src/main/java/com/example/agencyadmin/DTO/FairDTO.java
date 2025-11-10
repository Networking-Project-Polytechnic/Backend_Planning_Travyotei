package com.example.agencyadmin.DTO;

import java.util.UUID;

public class FairDTO {
    private UUID fairid;
    private float fairamount;
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
