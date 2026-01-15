package com.example.agencyadmin.Mappers;

import org.springframework.stereotype.Component;
import com.example.agencyadmin.DTOs.VehicleAmenityDTO;
import com.example.agencyadmin.Models.VehicleAmenity;

@Component
public class VehicleAmenityMapper {

    public VehicleAmenityDTO toDTO(VehicleAmenity amenity) {
        if (amenity == null) {
            return null;
        }
        VehicleAmenityDTO dto = new VehicleAmenityDTO();
        dto.setAmenityId(amenity.getAmenityId());
        dto.setAmenityName(amenity.getAmenityName());
        dto.setDescription(amenity.getDescription());
        return dto;
    }

    public VehicleAmenity toEntity(VehicleAmenityDTO dto) {
        if (dto == null) {
            return null;
        }
        VehicleAmenity amenity = new VehicleAmenity();
        amenity.setAmenityId(dto.getAmenityId());
        amenity.setAmenityName(dto.getAmenityName());
        amenity.setDescription(dto.getDescription());
        return amenity;
    }
}
