package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.ScheduleDTO;
import com.example.agencyadmin.Models.Schedule;
import com.example.agencyadmin.Repositories.ScheduleRepository;
import com.example.agencyadmin.Mappers.ScheduleMapper;

/**
 * Service class for Schedule entity.
 * This service encapsulates business logic for Schedule operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to schedules should be implemented in this
 * service.
 */
@Service
public class ScheduleService {

    /** The Schedule repository for database operations */
    @Autowired
    private ScheduleRepository scheduleRepository;

    /** The Schedule mapper for converting between entities and DTOs */
    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * Create a new schedule
     * 
     * @param scheduleDTO the schedule data transfer object
     * @return the created schedule DTO
     */
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toDTO(savedSchedule);
    }

    /**
     * Get a schedule by its ID
     * 
     * @param scheduleId the ID of the schedule
     * @return the schedule DTO if found
     */
    public Optional<ScheduleDTO> getScheduleById(UUID scheduleId) {
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleId);
        return schedule.map(scheduleMapper::toDTO);
    }

    /**
     * Get all schedules
     * 
     * @return list of all schedule DTOs
     */
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return list of schedule DTOs for the agency
     */
    public List<ScheduleDTO> getSchedulesByAgency(String agencyId) {
        List<Schedule> schedules = scheduleRepository.findByAgencyid(agencyId);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific route
     * 
     * @param routeId the ID of the route
     * @return list of schedule DTOs for the route
     */
    public List<ScheduleDTO> getSchedulesByRoute(UUID routeId) {
        List<Schedule> schedules = scheduleRepository.findByRouteid(routeId);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific bus
     * 
     * @param busId the ID of the bus
     * @return list of schedule DTOs for the bus
     */
    public List<ScheduleDTO> getSchedulesByBus(UUID busId) {
        List<Schedule> schedules = scheduleRepository.findByBusid(busId);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Get all schedules for a specific date
     * 
     * @param date the date of the schedules
     * @return list of schedule DTOs for the date
     */
    public List<ScheduleDTO> getSchedulesByDate(String date) {
        List<Schedule> schedules = scheduleRepository.findByDate(date);
        return schedules.stream().map(scheduleMapper::toDTO).toList();
    }

    /**
     * Update an existing schedule
     * 
     * @param scheduleId  the ID of the schedule to update
     * @param scheduleDTO the updated schedule data
     * @return the updated schedule DTO
     */
    public Optional<ScheduleDTO> updateSchedule(UUID scheduleId, ScheduleDTO scheduleDTO) {
        Optional<Schedule> existingSchedule = scheduleRepository.findById(scheduleId);
        if (existingSchedule.isPresent()) {
            Schedule schedule = existingSchedule.get();
            schedule.setDate(scheduleDTO.getDate());
            schedule.setArrivaltime(scheduleDTO.getArrivaltime());
            schedule.setDeparturetime(scheduleDTO.getDeparturetime());
            schedule.setRouteid(scheduleDTO.getRouteid());
            schedule.setBusid(scheduleDTO.getBusid());
            schedule.setAgencyid(scheduleDTO.getAgencyid());
            schedule.setPriceid(scheduleDTO.getPriceid());
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            return Optional.of(scheduleMapper.toDTO(updatedSchedule));
        }

        return Optional.empty();
    }

    /**
     * Delete a schedule by its ID
     * 
     * @param scheduleId the ID of the schedule to delete
     * @return true if deletion was successful
     */
    public boolean deleteSchedule(UUID scheduleId) {
        if (scheduleRepository.existsById(scheduleId)) {
            scheduleRepository.deleteById(scheduleId);
            return true;
        }
        return false;
    }
}
