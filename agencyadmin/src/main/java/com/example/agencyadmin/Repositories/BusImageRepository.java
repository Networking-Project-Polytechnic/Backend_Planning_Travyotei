package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusImage;

public interface BusImageRepository extends JpaRepository<BusImage, UUID> {
    List<BusImage> findByBusId(UUID busId);

    Optional<BusImage> findByPublicId(String publicId);

    List<BusImage> findByBusIdAndIsPrimary(UUID busId, Boolean isPrimary);

    Optional<BusImage> findFirstByBusIdAndIsPrimaryTrue(UUID busId);

    List<BusImage> findByBusIdIn(List<UUID> busIds);

    void deleteByBusId(UUID busId);
}
