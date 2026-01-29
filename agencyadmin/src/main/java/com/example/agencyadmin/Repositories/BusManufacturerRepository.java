package com.example.agencyadmin.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusManufacturers;

public interface BusManufacturerRepository extends JpaRepository<BusManufacturers, UUID> {
    Optional<BusManufacturers> findByManufacturerName(String manufacturerName);

    java.util.List<BusManufacturers> findByAgencyid(String agencyid);
}