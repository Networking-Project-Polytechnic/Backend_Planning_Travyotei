package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.agencyadmin.Models.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, UUID> {
    List<Location> findByAgencyid(String agencyid);
    Boolean  existsByAgencyidAndLocationname(String agencyid,String locationname);
    
}
