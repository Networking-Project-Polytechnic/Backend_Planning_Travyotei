package com.example.agencyadmin.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusType;

public interface BusTypeRepository extends JpaRepository<BusType, UUID> {
    Optional<BusType> findByBusTypeName(String busTypeName);

    java.util.List<BusType> findByAgencyid(String agencyid);
}
