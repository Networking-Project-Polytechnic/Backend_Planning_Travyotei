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

    @PutMapping("/{amenityId}")
    public ResponseEntity<VehicleAmenityDTO> updateAmenity(@PathVariable UUID amenityId,
            @RequestBody VehicleAmenityDTO dto) {
        return service.updateAmenity(amenityId, dto)
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
}
