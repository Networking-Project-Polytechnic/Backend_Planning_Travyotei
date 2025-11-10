package com.example.agencyadmin.DTO;

import java.util.UUID;

public class LocationDTO {
    private UUID locationid;
    private String locationname;
    private String agencyid;
    
    public UUID getLocationid() {
        return locationid;
    }
    public void setLocationid(UUID locationid) {
        this.locationid = locationid;
    }
    public String getLocationname() {
        return locationname;
    }
    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }
    public String getAgencyid() {
        return agencyid;
    }
    public void setAgencyid(String agencyid) {
        this.agencyid = agencyid;
    }

    
    
}
