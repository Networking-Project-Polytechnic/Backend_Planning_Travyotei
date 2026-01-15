package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusImage;

public interface BusImageRepository extends JpaRepository<BusImage, UUID> {
    List<BusImage> findByBusId(UUID busId);
    Optional<BusImage> findByS3Key(String s3Key);
    List<BusImage> findByBusIdAndIsPrimary(UUID busId, Boolean isPrimary);
    Optional<BusImage> findFirstByBusIdAndIsPrimaryTrue(UUID busId);
}

