package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.BusModelDTO;
import com.example.agencyadmin.Services.BusModelService;

/**
 * REST Controller for BusModels entity.
 * Handles HTTP requests related to bus model operations.
 * Provides endpoints for CRUD operations and bus model queries.
 * 
 * Base path: /api/v1/bus-models
 */
@RestController
@RequestMapping("/api/v1/bus-models")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusModelController {

    /** The BusModel service for business logic */
    @Autowired
    private BusModelService busModelService;

    /**
     * Create a new bus model
     * 
     * @param busModelDTO the bus model data transfer object
     * @return ResponseEntity with the created bus model DTO and HTTP 201 Created
     *         status
     */
    @PostMapping
    public ResponseEntity<BusModelDTO> createBusModel(@RequestBody BusModelDTO busModelDTO) {
        BusModelDTO createdBusModel = busModelService.createBusModel(busModelDTO);
        return new ResponseEntity<>(createdBusModel, HttpStatus.CREATED);
    }

    /**
     * Create a new bus model for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param busModelDTO the bus model data
     * @return ResponseEntity with the created bus model DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<BusModelDTO> createBusModelScoped(@PathVariable String agencyId,
            @RequestBody BusModelDTO busModelDTO) {
        BusModelDTO createdBusModel = busModelService.createBusModelScoped(agencyId, busModelDTO);
        return new ResponseEntity<>(createdBusModel, HttpStatus.CREATED);
    }

    /**
     * Get a bus model by its ID
     * 
     * @param busModelId the ID of the bus model
     * @return ResponseEntity with the bus model DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/{busModelId}")
    public ResponseEntity<BusModelDTO> getBusModelById(@PathVariable UUID busModelId) {
        return busModelService.getBusModelById(busModelId)
                .map(model -> new ResponseEntity<>(model, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all bus models
     * 
     * @return ResponseEntity with list of all bus model DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<BusModelDTO>> getAllBusModels() {
        List<BusModelDTO> busModels = busModelService.getAllBusModels();
        return new ResponseEntity<>(busModels, HttpStatus.OK);
    }

    /**
     * Get all bus models for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of bus model DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<BusModelDTO>> getBusModelsByAgency(@PathVariable String agencyId) {
        List<BusModelDTO> busModels = busModelService.getBusModelsByAgency(agencyId);
        return new ResponseEntity<>(busModels, HttpStatus.OK);
    }

    /**
     * Get a bus model by its name
     * 
     * @param modelName the name of the bus model
     * @return ResponseEntity with the bus model DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/name/{modelName}")
    public ResponseEntity<BusModelDTO> getBusModelByName(@PathVariable String modelName) {
        return busModelService.getBusModelByName(modelName)
                .map(model -> new ResponseEntity<>(model, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus model
     * 
     * @param busModelId  the ID of the bus model to update
     * @param busModelDTO the updated bus model data
     * @return ResponseEntity with the updated bus model DTO if successful,
     *         otherwise 404 Not Found
     */
    @PutMapping("/{busModelId}")
    public ResponseEntity<BusModelDTO> updateBusModel(@PathVariable UUID busModelId,
            @RequestBody BusModelDTO busModelDTO) {
        return busModelService.updateBusModel(busModelId, busModelDTO)
                .map(model -> new ResponseEntity<>(model, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus model for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param busModelId  the ID of the bus model to update
     * @param busModelDTO the updated bus model data
     * @return ResponseEntity with the updated bus model DTO if successful
     */
    @PutMapping("/agency/{agencyId}/{busModelId}")
    public ResponseEntity<BusModelDTO> updateBusModelScoped(@PathVariable String agencyId,
            @PathVariable UUID busModelId,
            @RequestBody BusModelDTO busModelDTO) {
        return busModelService.updateBusModelScoped(agencyId, busModelId, busModelDTO)
                .map(model -> new ResponseEntity<>(model, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a bus model by its ID
     * 
     * @param busModelId the ID of the bus model to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{busModelId}")
    public ResponseEntity<Void> deleteBusModel(@PathVariable UUID busModelId) {
        if (busModelService.deleteBusModel(busModelId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a bus model for a specific agency
     * 
     * @param agencyId   the ID of the agency
     * @param busModelId the ID of the bus model to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{busModelId}")
    public ResponseEntity<Void> deleteBusModelScoped(@PathVariable String agencyId, @PathVariable UUID busModelId) {
        if (busModelService.deleteBusModelScoped(agencyId, busModelId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
