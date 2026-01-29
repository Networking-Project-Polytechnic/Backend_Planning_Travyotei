package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.ScheduleDTO;
import com.example.agencyadmin.Models.Schedule;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Schedule entity and ScheduleDTO.
 * This mapper is responsible for converting Schedule JPA entities to DTOs and
 * vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class ScheduleMapper {

    /**
     * Converts a Schedule entity to ScheduleDTO
     * 
     * @param schedule the Schedule entity
     * @return ScheduleDTO containing the data from the Schedule entity
     */
    public ScheduleDTO toDTO(Schedule schedule) {
        if (schedule == null) {
            return null;
        }
        return new ScheduleDTO(
                schedule.getScheduleid(),
                schedule.getDate(),
                schedule.getArrivaltime(),
                schedule.getDeparturetime(),
                schedule.getRouteid(),
                schedule.getBusid(),
                schedule.getAgencyid(),
                schedule.getPriceid(),
                schedule.getDriverid());
    }

    /**
     * Converts a ScheduleDTO to Schedule entity
     * 
     * @param scheduleDTO the ScheduleDTO
     * @return Schedule entity containing the data from the DTO
     */
    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        if (scheduleDTO == null) {
            return null;
        }
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setArrivaltime(scheduleDTO.getArrivaltime());
        schedule.setDeparturetime(scheduleDTO.getDeparturetime());
        schedule.setRouteid(scheduleDTO.getRouteid());
        schedule.setBusid(scheduleDTO.getBusid());
        schedule.setAgencyid(scheduleDTO.getAgencyid());
        schedule.setPriceid(scheduleDTO.getPriceid());
        schedule.setDriverid(scheduleDTO.getDriverid());
        return schedule;
    }
}
