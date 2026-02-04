package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.Bus;

public interface BusRepository extends JpaRepository<Bus, UUID> {
    List<Bus> findByAgencyId(String agencyId);

    Bus findByRegistrationNumber(String registrationNumber);
}