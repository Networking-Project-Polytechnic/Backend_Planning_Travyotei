package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.BusTypeDTO;
import com.example.agencyadmin.Services.BusTypeService;

/**
 * REST Controller for BusType entity.
 * Handles HTTP requests related to bus type operations.
 * Provides endpoints for CRUD operations and bus type queries.
 * 
 * Base path: /api/v1/bus-types
 */
@RestController
@RequestMapping("/api/v1/bus-types")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusTypeController {

    /** The BusType service for business logic */
    @Autowired
    private BusTypeService busTypeService;

    /**
     * Create a new bus type
     * 
     * @param busTypeDTO the bus type data transfer object
     * @return ResponseEntity with the created bus type DTO and HTTP 201 Created
     *         status
     */
    @PostMapping
    public ResponseEntity<BusTypeDTO> createBusType(@RequestBody BusTypeDTO busTypeDTO) {
        BusTypeDTO createdBusType = busTypeService.createBusType(busTypeDTO);
        return new ResponseEntity<>(createdBusType, HttpStatus.CREATED);
    }

    /**
     * Create a new bus type for a specific agency
     * 
     * @param agencyId   the ID of the agency
     * @param busTypeDTO the bus type data
     * @return ResponseEntity with the created bus type DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<BusTypeDTO> createBusTypeScoped(@PathVariable String agencyId,
            @RequestBody BusTypeDTO busTypeDTO) {
        BusTypeDTO createdBusType = busTypeService.createBusTypeScoped(agencyId, busTypeDTO);
        return new ResponseEntity<>(createdBusType, HttpStatus.CREATED);
    }

    /**
     * Get a bus type by its ID
     * 
     * @param busTypeId the ID of the bus type
     * @return ResponseEntity with the bus type DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/{busTypeId}")
    public ResponseEntity<BusTypeDTO> getBusTypeById(@PathVariable UUID busTypeId) {
        return busTypeService.getBusTypeById(busTypeId)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all bus types
     * 
     * @return ResponseEntity with list of all bus type DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<BusTypeDTO>> getAllBusTypes() {
        List<BusTypeDTO> busTypes = busTypeService.getAllBusTypes();
        return new ResponseEntity<>(busTypes, HttpStatus.OK);
    }

    /**
     * Get all bus types for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of bus type DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<BusTypeDTO>> getBusTypesByAgency(@PathVariable String agencyId) {
        List<BusTypeDTO> busTypes = busTypeService.getBusTypesByAgency(agencyId);
        return new ResponseEntity<>(busTypes, HttpStatus.OK);
    }

    /**
     * Get a bus type by its name
     * 
     * @param busTypeName the name of the bus type
     * @return ResponseEntity with the bus type DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/name/{busTypeName}")
    public ResponseEntity<BusTypeDTO> getBusTypeByName(@PathVariable String busTypeName) {
        return busTypeService.getBusTypeByName(busTypeName)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus type
     * 
     * @param busTypeId  the ID of the bus type to update
     * @param busTypeDTO the updated bus type data
     * @return ResponseEntity with the updated bus type DTO if successful, otherwise
     *         404 Not Found
     */
    @PutMapping("/{busTypeId}")
    public ResponseEntity<BusTypeDTO> updateBusType(@PathVariable UUID busTypeId, @RequestBody BusTypeDTO busTypeDTO) {
        return busTypeService.updateBusType(busTypeId, busTypeDTO)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus type for a specific agency
     * 
     * @param agencyId   the ID of the agency
     * @param busTypeId  the ID of the bus type to update
     * @param busTypeDTO the updated bus type data
     * @return ResponseEntity with the updated bus type DTO if successful
     */
    @PutMapping("/agency/{agencyId}/{busTypeId}")
    public ResponseEntity<BusTypeDTO> updateBusTypeScoped(@PathVariable String agencyId, @PathVariable UUID busTypeId,
            @RequestBody BusTypeDTO busTypeDTO) {
        return busTypeService.updateBusTypeScoped(agencyId, busTypeId, busTypeDTO)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a bus type by its ID
     * 
     * @param busTypeId the ID of the bus type to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{busTypeId}")
    public ResponseEntity<Void> deleteBusType(@PathVariable UUID busTypeId) {
        if (busTypeService.deleteBusType(busTypeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a bus type for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param busTypeId the ID of the bus type to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{busTypeId}")
    public ResponseEntity<Void> deleteBusTypeScoped(@PathVariable String agencyId, @PathVariable UUID busTypeId) {
        if (busTypeService.deleteBusTypeScoped(agencyId, busTypeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
