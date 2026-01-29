package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agencyadmin.Models.Assignments;

public interface AssignmentsRepository extends JpaRepository<Assignments, UUID> {
    java.util.List<Assignments> findByAgencyId(String agencyId);

    java.util.List<Assignments> findByDriverId(UUID driverId);

    java.util.List<Assignments> findByScheduleId(UUID scheduleId);

    void deleteByScheduleId(UUID scheduleId);
}
