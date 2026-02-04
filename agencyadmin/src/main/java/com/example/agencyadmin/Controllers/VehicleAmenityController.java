package com.example.agencyadmin.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

import com.example.agencyadmin.DTOs.VehicleAmenityDTO;
import com.example.agencyadmin.Services.VehicleAmenityService;

@RestController
@RequestMapping("/api/vehicle-amenities")
public class VehicleAmenityController {

    private final VehicleAmenityService service;

    public VehicleAmenityController(VehicleAmenityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VehicleAmenityDTO>> getAllAmenities() {
        return ResponseEntity.ok(service.getAllAmenities());
    }

    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<VehicleAmenityDTO>> getAmenitiesByAgency(@PathVariable String agencyId) {
        return ResponseEntity.ok(service.getAmenitiesByAgency(agencyId));
    }

    @GetMapping("/{amenityId}")
    public ResponseEntity<VehicleAmenityDTO> getAmenityById(@PathVariable UUID amenityId) {
        return service.getAmenityById(amenityId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<VehicleAmenityDTO> createAmenity(@RequestBody VehicleAmenityDTO dto) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(service.createAmenity(dto));
    }

    /**
     * Create a new vehicle amenity for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param dto      the amenity data
     * @return ResponseEntity with the created amenity DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<VehicleAmenityDTO> createAmenityScoped(@PathVariable String agencyId,
            @RequestBody VehicleAmenityDTO dto) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(service.createAmenityScoped(agencyId, dto));
    }

    @PutMapping("/{amenityId}")
    public ResponseEntity<VehicleAmenityDTO> updateAmenity(@PathVariable UUID amenityId,
            @RequestBody VehicleAmenityDTO dto) {
        return service.updateAmenity(amenityId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing vehicle amenity for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param amenityId the ID of the amenity to update
     * @param dto       the updated amenity data
     * @return ResponseEntity with the updated amenity DTO if successful
     */
    @PutMapping("/agency/{agencyId}/{amenityId}")
    public ResponseEntity<VehicleAmenityDTO> updateAmenityScoped(@PathVariable String agencyId,
            @PathVariable UUID amenityId, @RequestBody VehicleAmenityDTO dto) {
        return service.updateAmenityScoped(agencyId, amenityId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{amenityId}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable UUID amenityId) {
        if (service.deleteAmenity(amenityId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Delete a vehicle amenity for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param amenityId the ID of the amenity to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{amenityId}")
    public ResponseEntity<Void> deleteAmenityScoped(@PathVariable String agencyId, @PathVariable UUID amenityId) {
        if (service.deleteAmenityScoped(agencyId, amenityId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
