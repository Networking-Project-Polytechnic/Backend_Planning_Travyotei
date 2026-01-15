package com.example.agencyadmin.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.TransmissionType;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, UUID> {
    Optional<TransmissionType> findByTypeName(String typeName);
}