package com.example.agencyadmin.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.agencyadmin.Models.Fair;

@Repository
public interface LocationRepo extends JpaRepository<Fair, UUID> {
    
}
