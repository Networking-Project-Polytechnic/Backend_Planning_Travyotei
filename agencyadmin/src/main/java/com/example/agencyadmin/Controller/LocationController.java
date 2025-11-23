package com.example.agencyadmin.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.DTO.LocationDTO;
import com.example.agencyadmin.Models.Location;
import com.example.agencyadmin.Service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAlllocations() {
        List<LocationDTO> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
    @GetMapping("/{agencyId}")
    public ResponseEntity<List<LocationDTO>> getlocationsByAgencyId(@PathVariable String agencyId) {
        List<LocationDTO> locations = locationService.getAllLocationsByAgencyid(agencyId);

        return ResponseEntity.ok(locations);
    }
    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody LocationDTO LocationDTO) {
        Location createdLocation = locationService.createLocation(LocationDTO);
        return ResponseEntity.ok(createdLocation);
    }
    @PutMapping("/{locationid}")
    public ResponseEntity<Location> updateLocation( @PathVariable UUID locationid, @RequestBody LocationDTO LocationDTO) {
        Location updatedLocation = locationService.updateLocation(locationid, LocationDTO);
        return ResponseEntity.ok(updatedLocation);
    }   
    @DeleteMapping("/{locationid}")
    public ResponseEntity<Void> deleteFair(@PathVariable UUID locationid) {
        locationService.deleteLocation(locationid);
        return ResponseEntity.noContent().build();
    }
}
