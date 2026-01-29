package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusCanTransport;

public interface BusCanTransportRepository extends JpaRepository<BusCanTransport, UUID> {
    java.util.List<BusCanTransport> findByAgencyid(String agencyid);
}
