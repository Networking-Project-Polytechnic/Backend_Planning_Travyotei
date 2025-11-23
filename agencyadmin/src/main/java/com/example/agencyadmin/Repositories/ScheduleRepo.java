package com.example.agencyadmin.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.agencyadmin.Models.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, UUID> {
    List<Schedule> findByAgencyid(String agencyid);
    
}

