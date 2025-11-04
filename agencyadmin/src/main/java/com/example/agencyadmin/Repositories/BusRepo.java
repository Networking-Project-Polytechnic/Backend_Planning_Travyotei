package com.example.agencyadmin.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agencyadmin.Models.Buses;

@Repository
public interface BusRepo extends JpaRepository<Buses, UUID> {
    Optional<Buses> findByBusplatenumber(String busplatenumber);
    boolean existsByBusplatenumber(String busplatenumber);
        
}
