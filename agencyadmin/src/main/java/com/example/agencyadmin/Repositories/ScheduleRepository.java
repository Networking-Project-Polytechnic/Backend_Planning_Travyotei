package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.agencyadmin.Models.Schedule;

/**
 * Repository interface for Schedule entity.
 * Provides database operations for Schedule entities including CRUD operations
 * and custom query methods to interact with the 'schedule' table.
 * 
 * This repository is used by the Schedule service to perform all
 * database-related operations.
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    /**
     * Find all schedules for a specific agency
     * 
     * @param agencyid the ID of the agency
     * @return list of schedules for the agency
     */
    java.util.List<Schedule> findByAgencyid(String agencyid);

    /**
     * Find all schedules for a specific route
     * 
     * @param routeid the ID of the route
     * @return list of schedules for the route
     */
    java.util.List<Schedule> findByRouteid(UUID routeid);

    /**
     * Find all schedules for a specific bus
     * 
     * @param busid the ID of the bus
     * @return list of schedules for the bus
     */
    java.util.List<Schedule> findByBusid(UUID busid);

    /**
     * Find all schedules for a specific date
     * 
     * @param date the date of the schedules
     * @return list of schedules for the date
     */
    java.util.List<Schedule> findByDate(String date);

    /**
     * Find all schedules for a specific agency and date
     * 
     * @param agencyid the ID of the agency
     * @param date     the date
     * @return list of schedules
     */
    java.util.List<Schedule> findByAgencyidAndDate(String agencyid, String date);

    /**
     * Find all schedules for a specific agency, date, and departure time
     * 
     * @param agencyid      the ID of the agency
     * @param date          the date
     * @param departuretime the departure time
     * @return list of schedules
     */
    java.util.List<Schedule> findByAgencyidAndDateAndDeparturetime(String agencyid, String date, String departuretime);

    void deleteByBusid(UUID busid);
}
