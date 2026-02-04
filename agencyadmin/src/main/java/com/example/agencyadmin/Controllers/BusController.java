package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.BusDTO;
import com.example.agencyadmin.Services.BusService;

/**
 * REST Controller for Bus entity.
 * Handles HTTP requests related to bus operations.
 * Provides endpoints for CRUD operations and bus queries.
 * 
 * Base path: /api/v1/buses
 */
@RestController
@RequestMapping("/api/v1/buses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusController {

    /** The Bus service for business logic */
    @Autowired
    private BusService busService;

    /**
     * Create a new bus
     * 
     * @param busDTO the bus data transfer object
     * @return ResponseEntity with the created bus DTO and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<BusDTO> createBus(@RequestBody BusDTO busDTO) {
        BusDTO createdBus = busService.createBus(busDTO);
        return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
    }

    /**
     * Create a new bus for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param busDTO   the bus data
     * @return ResponseEntity with the created bus DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<BusDTO> createBusScoped(@PathVariable UUID agencyId, @RequestBody BusDTO busDTO) {
        BusDTO createdBus = busService.createBusScoped(agencyId, busDTO);
        return new ResponseEntity<>(createdBus, HttpStatus.CREATED);
    }

    /**
     * Get a bus by its ID
     * 
     * @param busId the ID of the bus
     * @return ResponseEntity with the bus DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/{busId}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable UUID busId) {
        return busService.getBusById(busId)
                .map(bus -> new ResponseEntity<>(bus, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all buses
     * 
     * @return ResponseEntity with list of all bus DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        List<BusDTO> buses = busService.getAllBuses();
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    /**
     * Get all buses for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of bus DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<BusDTO>> getBusesByAgency(@PathVariable UUID agencyId) {
        List<BusDTO> buses = busService.getBusesByAgency(agencyId);
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    /**
     * Get a bus by its registration number
     * 
     * @param registrationNumber the registration number
     * @return ResponseEntity with the bus DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<BusDTO> getBusByRegistrationNumber(@PathVariable String registrationNumber) {
        return busService.getBusByRegistrationNumber(registrationNumber)
                .map(bus -> new ResponseEntity<>(bus, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus
     * 
     * @param busId  the ID of the bus to update
     * @param busDTO the updated bus data
     * @return ResponseEntity with the updated bus DTO if successful, otherwise 404
     *         Not Found
     */
    @PutMapping("/{busId}")
    public ResponseEntity<BusDTO> updateBus(@PathVariable UUID busId, @RequestBody BusDTO busDTO) {
        return busService.updateBus(busId, busDTO)
                .map(bus -> new ResponseEntity<>(bus, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param busId    the ID of the bus to update
     * @param busDTO   the updated bus data
     * @return ResponseEntity with the updated bus DTO
     */
    @PutMapping("/agency/{agencyId}/{busId}")
    public ResponseEntity<BusDTO> updateBusScoped(@PathVariable UUID agencyId, @PathVariable UUID busId,
            @RequestBody BusDTO busDTO) {
        return busService.updateBusScoped(agencyId, busId, busDTO)
                .map(bus -> new ResponseEntity<>(bus, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a bus by its ID
     * 
     * @param busId the ID of the bus to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{busId}")
    public ResponseEntity<Void> deleteBus(@PathVariable UUID busId) {
        if (busService.deleteBus(busId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a bus for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param busId    the ID of the bus to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{busId}")
    public ResponseEntity<Void> deleteBusScoped(@PathVariable UUID agencyId, @PathVariable UUID busId) {
        if (busService.deleteBusScoped(agencyId, busId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
