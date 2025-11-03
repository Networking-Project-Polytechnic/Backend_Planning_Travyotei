package com.example.agencyadmin.Models;

import jakarta.persistence.Entity;

//@Entity(name = "routes")
public class Route {
    private String routeid;
    private String startlocationid;
    private String endlocationid;
    private String agencyid;
}

