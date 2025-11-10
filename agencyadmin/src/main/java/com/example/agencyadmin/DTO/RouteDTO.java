package com.example.agencyadmin.DTO;

import java.util.UUID;

public class RouteDTO {
    private UUID routeid;
    private String startlocationid;
    private String endlocationid;
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
