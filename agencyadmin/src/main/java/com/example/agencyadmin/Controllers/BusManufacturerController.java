package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.BusManufacturerDTO;
import com.example.agencyadmin.Services.BusManufacturerService;

/**
 * REST Controller for BusManufacturers entity.
 * Handles HTTP requests related to bus manufacturer operations.
 * Provides endpoints for CRUD operations and bus manufacturer queries.
 * 
 * Base path: /api/v1/bus-manufacturers
 */
@RestController
@RequestMapping("/api/v1/bus-manufacturers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusManufacturerController {
    
    /** The BusManufacturer service for business logic */
    @Autowired
    private BusManufacturerService busManufacturerService;
    
    /**
     * Create a new bus manufacturer
     * @param busManufacturerDTO the bus manufacturer data transfer object
     * @return ResponseEntity with the created bus manufacturer DTO and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<BusManufacturerDTO> createBusManufacturer(@RequestBody BusManufacturerDTO busManufacturerDTO) {
        BusManufacturerDTO createdBusManufacturer = busManufacturerService.createBusManufacturer(busManufacturerDTO);
        return new ResponseEntity<>(createdBusManufacturer, HttpStatus.CREATED);
    }
    
    /**
     * Get a bus manufacturer by its ID
     * @param manufacturerId the ID of the bus manufacturer
     * @return ResponseEntity with the bus manufacturer DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/{manufacturerId}")
    public ResponseEntity<BusManufacturerDTO> getBusManufacturerById(@PathVariable UUID manufacturerId) {
        return busManufacturerService.getBusManufacturerById(manufacturerId)
                .map(manufacturer -> new ResponseEntity<>(manufacturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Get all bus manufacturers
     * @return ResponseEntity with list of all bus manufacturer DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<BusManufacturerDTO>> getAllBusManufacturers() {
        List<BusManufacturerDTO> busManufacturers = busManufacturerService.getAllBusManufacturers();
        return new ResponseEntity<>(busManufacturers, HttpStatus.OK);
    }
    
    /**
     * Get a bus manufacturer by its name
     * @param manufacturerName the name of the bus manufacturer
     * @return ResponseEntity with the bus manufacturer DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/name/{manufacturerName}")
    public ResponseEntity<BusManufacturerDTO> getBusManufacturerByName(@PathVariable String manufacturerName) {
        return busManufacturerService.getBusManufacturerByName(manufacturerName)
                .map(manufacturer -> new ResponseEntity<>(manufacturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Update an existing bus manufacturer
     * @param manufacturerId the ID of the bus manufacturer to update
     * @param busManufacturerDTO the updated bus manufacturer data
     * @return ResponseEntity with the updated bus manufacturer DTO if successful, otherwise 404 Not Found
     */
    @PutMapping("/{manufacturerId}")
    public ResponseEntity<BusManufacturerDTO> updateBusManufacturer(@PathVariable UUID manufacturerId, @RequestBody BusManufacturerDTO busManufacturerDTO) {
        return busManufacturerService.updateBusManufacturer(manufacturerId, busManufacturerDTO)
                .map(manufacturer -> new ResponseEntity<>(manufacturer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Delete a bus manufacturer by its ID
     * @param manufacturerId the ID of the bus manufacturer to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404 Not Found
     */
    @DeleteMapping("/{manufacturerId}")
    public ResponseEntity<Void> deleteBusManufacturer(@PathVariable UUID manufacturerId) {
        if (busManufacturerService.deleteBusManufacturer(manufacturerId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

