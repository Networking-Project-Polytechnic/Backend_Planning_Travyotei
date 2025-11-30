package com.example.agencyadmin.DTO;

import java.util.UUID;

public class RouteDTO {
    private UUID routeid;
    private UUID startlocationid;
    private UUID endlocationid;
    private String agencyid;
    
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

    
}
