package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.ScheduleDTO;
import com.example.agencyadmin.DTOs.ScheduleDetailsDTO;
import com.example.agencyadmin.Services.ScheduleService;

/**
 * REST Controller for Schedule entity.
 * Handles HTTP requests related to schedule operations.
 * Provides endpoints for CRUD operations and schedule queries.
 * 
 * Base path: /api/v1/schedules
 */
@RestController
@RequestMapping("/api/v1/schedules")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ScheduleController {

    /** The Schedule service for business logic */
    @Autowired
    private ScheduleService scheduleService;

    /**
     * Create a new schedule
     * 
     * @param scheduleDTO the schedule data transfer object
     * @return ResponseEntity with the created schedule DTO and HTTP 201 Created
     *         status
     */
    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO createdSchedule = scheduleService.createSchedule(scheduleDTO);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    /**
     * Get a schedule by its ID
     * 
     * @param scheduleId the ID of the schedule
     * @return ResponseEntity with the schedule DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable UUID scheduleId) {
        return scheduleService.getScheduleById(scheduleId)
                .map(schedule -> new ResponseEntity<>(schedule, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{scheduleId}/details")
    public ResponseEntity<ScheduleDetailsDTO> getScheduleDetails(@PathVariable UUID scheduleId) {
        return scheduleService.getScheduleDetails(scheduleId)
                .map(details -> new ResponseEntity<>(details, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all schedules
     * 
     * @return ResponseEntity with list of all schedule DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> schedules = scheduleService.getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    /**
     * Get all schedules for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of schedule DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByAgency(@PathVariable String agencyId) {
        List<ScheduleDTO> schedules = scheduleService.getSchedulesByAgency(agencyId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    /**
     * Get all schedules for a specific route
     * 
     * @param routeId the ID of the route
     * @return ResponseEntity with list of schedule DTOs for the route
     */
    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByRoute(@PathVariable UUID routeId) {
        List<ScheduleDTO> schedules = scheduleService.getSchedulesByRoute(routeId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    /**
     * Get all schedules for a specific bus
     * 
     * @param busId the ID of the bus
     * @return ResponseEntity with list of schedule DTOs for the bus
     */
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByBus(@PathVariable UUID busId) {
        List<ScheduleDTO> schedules = scheduleService.getSchedulesByBus(busId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    /**
     * Get all schedules for a specific date
     * 
     * @param date the date of the schedules
     * @return ResponseEntity with list of schedule DTOs for the date
     */
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByDate(@PathVariable String date) {
        List<ScheduleDTO> schedules = scheduleService.getSchedulesByDate(date);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    /**
     * Update an existing schedule
     * 
     * @param scheduleId  the ID of the schedule to update
     * @param scheduleDTO the updated schedule data
     * @return ResponseEntity with the updated schedule DTO if successful, otherwise
     *         404 Not Found
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDTO> updateSchedule(@PathVariable UUID scheduleId,
            @RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.updateSchedule(scheduleId, scheduleDTO)
                .map(schedule -> new ResponseEntity<>(schedule, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a schedule by its ID
     * 
     * @param scheduleId the ID of the schedule to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable UUID scheduleId) {
        if (scheduleService.deleteSchedule(scheduleId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
