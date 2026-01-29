package com.example.agencyadmin.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusMakes;

public interface BusMakeRepository extends JpaRepository<BusMakes, UUID> {
    Optional<BusMakes> findByMakeName(String makeName);

    java.util.List<BusMakes> findByAgencyid(String agencyid);
}