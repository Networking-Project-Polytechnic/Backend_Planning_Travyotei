package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.FuelTypeDTO;
import com.example.agencyadmin.Services.FuelTypeService;

/**
 * REST Controller for FuelType entity.
 * Handles HTTP requests related to fuel type operations.
 * Provides endpoints for CRUD operations and fuel type queries.
 * 
 * Base path: /api/v1/fuel-types
 */
@RestController
@RequestMapping("/api/v1/fuel-types")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FuelTypeController {
    
    /** The FuelType service for business logic */
    @Autowired
    private FuelTypeService fuelTypeService;
    
    /**
     * Create a new fuel type
     * @param fuelTypeDTO the fuel type data transfer object
     * @return ResponseEntity with the created fuel type DTO and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<FuelTypeDTO> createFuelType(@RequestBody FuelTypeDTO fuelTypeDTO) {
        FuelTypeDTO createdFuelType = fuelTypeService.createFuelType(fuelTypeDTO);
        return new ResponseEntity<>(createdFuelType, HttpStatus.CREATED);
    }
    
    /**
     * Get a fuel type by its ID
     * @param fuelTypeId the ID of the fuel type
     * @return ResponseEntity with the fuel type DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/{fuelTypeId}")
    public ResponseEntity<FuelTypeDTO> getFuelTypeById(@PathVariable UUID fuelTypeId) {
        return fuelTypeService.getFuelTypeById(fuelTypeId)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Get all fuel types
     * @return ResponseEntity with list of all fuel type DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<FuelTypeDTO>> getAllFuelTypes() {
        List<FuelTypeDTO> fuelTypes = fuelTypeService.getAllFuelTypes();
        return new ResponseEntity<>(fuelTypes, HttpStatus.OK);
    }
    
    /**
     * Get a fuel type by its name
     * @param fuelTypeName the name of the fuel type
     * @return ResponseEntity with the fuel type DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/name/{fuelTypeName}")
    public ResponseEntity<FuelTypeDTO> getFuelTypeByName(@PathVariable String fuelTypeName) {
        return fuelTypeService.getFuelTypeByName(fuelTypeName)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Update an existing fuel type
     * @param fuelTypeId the ID of the fuel type to update
     * @param fuelTypeDTO the updated fuel type data
     * @return ResponseEntity with the updated fuel type DTO if successful, otherwise 404 Not Found
     */
    @PutMapping("/{fuelTypeId}")
    public ResponseEntity<FuelTypeDTO> updateFuelType(@PathVariable UUID fuelTypeId, @RequestBody FuelTypeDTO fuelTypeDTO) {
        return fuelTypeService.updateFuelType(fuelTypeId, fuelTypeDTO)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Delete a fuel type by its ID
     * @param fuelTypeId the ID of the fuel type to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404 Not Found
     */
    @DeleteMapping("/{fuelTypeId}")
    public ResponseEntity<Void> deleteFuelType(@PathVariable UUID fuelTypeId) {
        if (fuelTypeService.deleteFuelType(fuelTypeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
