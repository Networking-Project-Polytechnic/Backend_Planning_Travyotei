package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.BusModel;

public interface BusModelRepository extends JpaRepository<BusModel, UUID> {}