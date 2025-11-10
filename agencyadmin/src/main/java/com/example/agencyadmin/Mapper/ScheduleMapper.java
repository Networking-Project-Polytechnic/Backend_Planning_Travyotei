package com.example.agencyadmin.Mapper;

import com.example.agencyadmin.DTO.ScheduleDTO;
import com.example.agencyadmin.Models.Schedule;

public class ScheduleMapper {

    public static Schedule toEntity(ScheduleDTO dto){
        Schedule schedule = new Schedule();
        schedule.setDate(dto.getDate());
        schedule.setDeparturetime(dto.getDeparturetime());
        schedule.setArrivaltime(dto.getArrivaltime());
        schedule.setBusid(dto.getBusid());
        schedule.setAgencyid(dto.getAgencyid());
        schedule.setRouteid(dto.getRouteid());
        return schedule;                                                    
    }
    
    public static ScheduleDTO toDto(Schedule schedule){
        ScheduleDTO dto = new ScheduleDTO();
        dto.setDate(schedule.getDate());
        dto.setDeparturetime(schedule.getDeparturetime());
        dto.setArrivaltime(schedule.getArrivaltime());
        dto.setBusid(schedule.getBusid());
        dto.setAgencyid(schedule.getAgencyid());
        dto.setRouteid(schedule.getRouteid());
        return dto;


    }

    public static void updateEntityFromDto(ScheduleDTO dto, Schedule schedule) {
        schedule.setDate(dto.getDate());
        schedule.setDeparturetime(dto.getDeparturetime());
        schedule.setArrivaltime(dto.getArrivaltime());
        schedule.setBusid(dto.getBusid());
        schedule.setAgencyid(dto.getAgencyid());
        schedule.setRouteid(dto.getRouteid());
    }
}

