package com.example.agencyadmin.Mapper;

import com.example.agencyadmin.DTO.BusesDto;
import com.example.agencyadmin.Models.Buses;

public class BusMapper {

    // Convert DTO to Entity
    public static Buses toEntity(BusesDto dto) {
        Buses bus = new Buses();
        bus.setBusplatenumber(dto.getBusplatenumber());
        bus.setBuscapacity(dto.getBuscapacity());
        bus.setBustype(dto.getBustype());
        bus.setAgencyid(dto.getAgencyid());
        bus.setTrackingid(dto.getTrackingid());
       
        return bus;
    }

    // Convert Entity to DTO
    public static BusesDto toDto(Buses bus) {
        BusesDto dto = new BusesDto();
        dto.setBusid(bus.getBusid());
        dto.setBusplatenumber(bus.getBusplatenumber());
        dto.setBuscapacity(bus.getBuscapacity());
        dto.setBustype(bus.getBustype());
        dto.setAgencyid(bus.getAgencyid());
        dto.setTrackingid(bus.getTrackingid());
        return dto;
    }
    public static void updateEntityFromDto(BusesDto dto, Buses bus) {
        bus.setBusplatenumber(dto.getBusplatenumber());
        bus.setBuscapacity(dto.getBuscapacity());
        bus.setBustype(dto.getBustype());
        bus.setAgencyid(dto.getAgencyid());
        bus.setTrackingid(dto.getTrackingid());
    }
}
