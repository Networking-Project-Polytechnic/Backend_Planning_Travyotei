package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusModels;

public interface BusModelRepository extends JpaRepository<BusModels, UUID> {
    Optional<BusModels> findByModelName(String modelName);
    List<BusModels> findByModelNameContaining(String modelName);
}