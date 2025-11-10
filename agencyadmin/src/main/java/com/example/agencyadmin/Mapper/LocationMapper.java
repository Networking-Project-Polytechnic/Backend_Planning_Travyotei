package com.example.agencyadmin.Mapper;

import com.example.agencyadmin.DTO.LocationDTO;
import com.example.agencyadmin.Models.Location;

public class LocationMapper {

    public static Location toEntity(LocationDTO dto){
        Location location = new Location();
        location.setLocationname(dto.getLocationname());
        location.setAgencyid(dto.getAgencyid());
        return location;
    }

    public static LocationDTO toDto(Location location){
        LocationDTO dto = new LocationDTO();
        dto.setLocationname(location.getLocationname());
        dto.setAgencyid(location.getAgencyid());
        return dto;
    }

    public static void updateEntityFromDto(LocationDTO dto, Location location) {
        location.setLocationname(dto.getLocationname());
        location.setAgencyid(dto.getAgencyid());
    }
    
}
