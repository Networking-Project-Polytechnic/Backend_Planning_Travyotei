package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.Driver;

public interface DriverRepository extends JpaRepository<Driver, UUID> {}