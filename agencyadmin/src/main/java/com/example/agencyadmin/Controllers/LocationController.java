package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.LocationDTO;
import com.example.agencyadmin.Services.LocationService;

/**
 * REST Controller for Location entity.
 * Handles HTTP requests related to location operations.
 * Provides endpoints for CRUD operations and location queries.
 * 
 * Base path: /api/v1/locations
 */
@RestController
@RequestMapping("/api/v1/locations")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LocationController {
    
    /** The Location service for business logic */
    @Autowired
    private LocationService locationService;
    
    /**
     * Create a new location
     * @param locationDTO the location data transfer object
     * @return ResponseEntity with the created location DTO and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        LocationDTO createdLocation = locationService.createLocation(locationDTO);
        return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
    }
    
    /**
     * Get a location by its ID
     * @param locationId the ID of the location
     * @return ResponseEntity with the location DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable UUID locationId) {
        return locationService.getLocationById(locationId)
                .map(location -> new ResponseEntity<>(location, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Get all locations
     * @return ResponseEntity with list of all location DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    /**
     * Get all locations for a specific agency
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of location DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<LocationDTO>> getLocationsByAgency(@PathVariable String agencyId) {
        List<LocationDTO> locations = locationService.getLocationsByAgency(agencyId);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    /**
     * Get a location by its name
     * @param locationName the name of the location
     * @return ResponseEntity with the location DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/name/{locationName}")
    public ResponseEntity<LocationDTO> getLocationByName(@PathVariable String locationName) {
        return locationService.getLocationByName(locationName)
                .map(location -> new ResponseEntity<>(location, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Update an existing location
     * @param locationId the ID of the location to update
     * @param locationDTO the updated location data
     * @return ResponseEntity with the updated location DTO if successful, otherwise 404 Not Found
     */
    @PutMapping("/{locationId}")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable UUID locationId, @RequestBody LocationDTO locationDTO) {
        return locationService.updateLocation(locationId, locationDTO)
                .map(location -> new ResponseEntity<>(location, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Delete a location by its ID
     * @param locationId the ID of the location to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404 Not Found
     */
    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID locationId) {
        if (locationService.deleteLocation(locationId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
