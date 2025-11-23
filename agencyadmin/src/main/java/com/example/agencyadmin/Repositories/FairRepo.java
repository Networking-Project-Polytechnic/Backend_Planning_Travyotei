package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agencyadmin.Models.Fair;

@Repository
public interface FairRepo extends JpaRepository<Fair, UUID> {
    List<Fair> findByAgencyid(String agencyid);
    Boolean existsByAgencyidAndFairamount(String agencyid, float fairamount);

    
}
