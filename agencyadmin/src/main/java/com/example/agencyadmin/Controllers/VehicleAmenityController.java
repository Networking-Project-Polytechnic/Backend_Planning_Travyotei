package com.example.agencyadmin.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseEntity<VehicleAmenityDTO> createAmenity(@RequestBody VehicleAmenityDTO dto) {
        return ResponseEntity.ok(service.createAmenity(dto));
    }
}
