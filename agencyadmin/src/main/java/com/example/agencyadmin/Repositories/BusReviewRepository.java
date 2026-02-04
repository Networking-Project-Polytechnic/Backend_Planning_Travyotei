package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusReview;

public interface BusReviewRepository extends JpaRepository<BusReview, UUID> {
    java.util.List<com.example.agencyadmin.Models.BusReview> findByBusBusIdIn(java.util.List<UUID> busIds);

    java.util.List<com.example.agencyadmin.Models.BusReview> findByAgencyId(String agencyId);

    void deleteByBusBusId(UUID busId);
}
