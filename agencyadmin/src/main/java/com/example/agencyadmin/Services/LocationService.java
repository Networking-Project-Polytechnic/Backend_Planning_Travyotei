package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.LocationDTO;
import com.example.agencyadmin.Models.Location;
import com.example.agencyadmin.Repositories.LocationRepository;
import com.example.agencyadmin.Mappers.LocationMapper;

/**
 * Service class for Location entity.
 * This service encapsulates business logic for Location operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to locations should be implemented in this service.
 */
@Service
public class LocationService {
    
    /** The Location repository for database operations */
    @Autowired
    private LocationRepository locationRepository;
    
    /** The Location mapper for converting between entities and DTOs */
    @Autowired
    private LocationMapper locationMapper;
    
    /**
     * Create a new location
     * @param locationDTO the location data transfer object
     * @return the created location DTO
     */
    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = locationMapper.toEntity(locationDTO);
        Location savedLocation = locationRepository.save(location);
        return locationMapper.toDTO(savedLocation);
    }
    
    /**
     * Get a location by its ID
     * @param locationId the ID of the location
     * @return the location DTO if found
     */
    public Optional<LocationDTO> getLocationById(UUID locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        return location.map(locationMapper::toDTO);
    }
    
    /**
     * Get all locations
     * @return list of all location DTOs
     */
    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(locationMapper::toDTO).toList();
    }
    
    /**
     * Get all locations for a specific agency
     * @param agencyId the ID of the agency
     * @return list of location DTOs for the agency
     */
    public List<LocationDTO> getLocationsByAgency(String agencyId) {
        List<Location> locations = locationRepository.findByAgencyid(agencyId);
        return locations.stream().map(locationMapper::toDTO).toList();
    }
    
    /**
     * Get a location by its name
     * @param locationName the name of the location
     * @return the location DTO if found
     */
    public Optional<LocationDTO> getLocationByName(String locationName) {
        Location location = locationRepository.findByLocationname(locationName);
        return Optional.ofNullable(locationMapper.toDTO(location));
    }
    
    /**
     * Update an existing location
     * @param locationId the ID of the location to update
     * @param locationDTO the updated location data
     * @return the updated location DTO
     */
    public Optional<LocationDTO> updateLocation(UUID locationId, LocationDTO locationDTO) {
        Optional<Location> existingLocation = locationRepository.findById(locationId);
        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            location.setLocationname(locationDTO.getLocationname());
            location.setAgencyid(locationDTO.getAgencyid());
            Location updatedLocation = locationRepository.save(location);
            return Optional.of(locationMapper.toDTO(updatedLocation));
        }
        return Optional.empty();
    }
    
    /**
     * Delete a location by its ID
     * @param locationId the ID of the location to delete
     * @return true if deletion was successful
     */
    public boolean deleteLocation(UUID locationId) {
        if (locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
            return true;
        }
        return false;
    }
}
