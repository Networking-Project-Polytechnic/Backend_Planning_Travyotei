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

import com.example.agencyadmin.DTOs.BusCanTransportDTO;
import com.example.agencyadmin.Services.BusCanTransportService;

@RestController
@RequestMapping("/api/bus-transportables")
public class BusCanTransportController {

    private final BusCanTransportService service;

    public BusCanTransportController(BusCanTransportService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BusCanTransportDTO>> getAllTransportables() {
        return ResponseEntity.ok(service.getAllTransportables());
    }

    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<BusCanTransportDTO>> getTransportablesByAgency(@PathVariable String agencyId) {
        return ResponseEntity.ok(service.getTransportablesByAgency(agencyId));
    }

    @GetMapping("/{transportId}")
    public ResponseEntity<BusCanTransportDTO> getTransportableById(@PathVariable UUID transportId) {
        return service.getTransportableById(transportId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BusCanTransportDTO> createTransportable(@RequestBody BusCanTransportDTO dto) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(service.createTransportable(dto));
    }

    /**
     * Create a new transportable item for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param dto      the transportable item data
     * @return ResponseEntity with the created transportable item DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<BusCanTransportDTO> createTransportableScoped(@PathVariable String agencyId,
            @RequestBody BusCanTransportDTO dto) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(service.createTransportableScoped(agencyId, dto));
    }

    @PutMapping("/{transportId}")
    public ResponseEntity<BusCanTransportDTO> updateTransportable(@PathVariable UUID transportId,
            @RequestBody BusCanTransportDTO dto) {
        return service.updateTransportable(transportId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing transportable item for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param transportId the ID of the transportable item to update
     * @param dto         the updated transportable item data
     * @return ResponseEntity with the updated transportable item DTO if successful
     */
    @PutMapping("/agency/{agencyId}/{transportId}")
    public ResponseEntity<BusCanTransportDTO> updateTransportableScoped(@PathVariable String agencyId,
            @PathVariable UUID transportId, @RequestBody BusCanTransportDTO dto) {
        return service.updateTransportableScoped(agencyId, transportId, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{transportId}")
    public ResponseEntity<Void> deleteTransportable(@PathVariable UUID transportId) {
        if (service.deleteTransportable(transportId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Delete a transportable item for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param transportId the ID of the transportable item to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{transportId}")
    public ResponseEntity<Void> deleteTransportableScoped(@PathVariable String agencyId,
            @PathVariable UUID transportId) {
        if (service.deleteTransportableScoped(agencyId, transportId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
