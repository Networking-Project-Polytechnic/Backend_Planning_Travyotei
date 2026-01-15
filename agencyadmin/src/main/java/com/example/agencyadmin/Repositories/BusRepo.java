package com.example.agencyadmin.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agencyadmin.Models.Bus;

@Repository
public interface BusRepo extends JpaRepository<Bus, UUID> {
    Optional<Bus> findByRegistrationNumber(String registrationNumber);
    boolean existsByRegistrationNumber(String registrationNumber);
        
}
