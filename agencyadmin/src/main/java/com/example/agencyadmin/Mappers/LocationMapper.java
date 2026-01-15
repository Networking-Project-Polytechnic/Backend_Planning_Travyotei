package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.LocationDTO;
import com.example.agencyadmin.Models.Location;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Location entity and LocationDTO.
 * This mapper is responsible for converting Location JPA entities to DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class LocationMapper {
    
    /**
     * Converts a Location entity to LocationDTO
     * @param location the Location entity
     * @return LocationDTO containing the data from the Location entity
     */
    public LocationDTO toDTO(Location location) {
        if (location == null) {
            return null;
        }
        return new LocationDTO(
            location.getLocationid(),
            location.getLocationname(),
            location.getAgencyid()
        );
    }
    
    /**
     * Converts a LocationDTO to Location entity
     * @param locationDTO the LocationDTO
     * @return Location entity containing the data from the DTO
     */
    public Location toEntity(LocationDTO locationDTO) {
        if (locationDTO == null) {
            return null;
        }
        Location location = new Location();
        location.setLocationname(locationDTO.getLocationname());
        location.setAgencyid(locationDTO.getAgencyid());
        return location;
    }
}
