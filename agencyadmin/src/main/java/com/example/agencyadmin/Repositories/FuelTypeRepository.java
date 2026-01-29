package com.example.agencyadmin.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.FuelType;

public interface FuelTypeRepository extends JpaRepository<FuelType, UUID> {
    Optional<FuelType> findByFuelTypeName(String fuelTypename);

    java.util.List<FuelType> findByAgencyid(String agencyid);
}