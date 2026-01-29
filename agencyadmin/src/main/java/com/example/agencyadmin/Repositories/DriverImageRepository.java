package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.DriverImage;

public interface DriverImageRepository extends JpaRepository<DriverImage, UUID> {
    List<DriverImage> findByDriverId(UUID driverId);

    Optional<DriverImage> findByPublicId(String publicId);

    List<DriverImage> findByDriverIdAndIsPrimary(UUID driverId, Boolean isPrimary);

    Optional<DriverImage> findFirstByDriverIdAndIsPrimaryTrue(UUID driverId);

    List<DriverImage> findByDriverIdIn(List<UUID> driverIds);
}
