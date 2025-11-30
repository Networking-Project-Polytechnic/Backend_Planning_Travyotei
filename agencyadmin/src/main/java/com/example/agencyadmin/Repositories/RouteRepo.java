package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.agencyadmin.Models.Route;

@Repository
public interface RouteRepo extends JpaRepository<Route, UUID> {
    List<Route> findByAgencyid(String agencyid);
    Boolean existsByAgencyidAndStartlocationidAndEndlocationid(String agencyid,UUID startlocationid,UUID endlocationid);
    
}

