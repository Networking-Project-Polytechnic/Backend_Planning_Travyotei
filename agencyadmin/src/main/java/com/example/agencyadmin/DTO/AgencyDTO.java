package com.example.agencyadmin.DTO;

import java.util.UUID;

public class AgencyDTO {
    private UUID scheduleid;
    private String arrivaltime;
    private String departuretime;
    private int buscapacity;
    private float fairamount;
    
    public UUID getScheduleid() {
        return scheduleid;
    }
    public void setScheduleid(UUID scheduleid) {
        this.scheduleid = scheduleid;
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
    public int getBuscapacity() {
        return buscapacity;
    }
    public void setBuscapacity(int buscapacity) {
        this.buscapacity = buscapacity;
    }
    public float getFairamount() {
        return fairamount;
    }
    public void setFairamount(float fairamount) {
        this.fairamount = fairamount;
    }
    
}
