package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.TransmissionTypeDTO;
import com.example.agencyadmin.Services.TransmissionTypeService;

/**
 * REST Controller for TransmissionType entity.
 * Handles HTTP requests related to transmission type operations.
 * Provides endpoints for CRUD operations and transmission type queries.
 * 
 * Base path: /api/v1/transmission-types
 */
@RestController
@RequestMapping("/api/v1/transmission-types")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TransmissionTypeController {

    /** The TransmissionType service for business logic */
    @Autowired
    private TransmissionTypeService transmissionTypeService;

    /**
     * Create a new transmission type
     * 
     * @param transmissionTypeDTO the transmission type data transfer object
     * @return ResponseEntity with the created transmission type DTO and HTTP 201
     *         Created status
     */
    @PostMapping
    public ResponseEntity<TransmissionTypeDTO> createTransmissionType(
            @RequestBody TransmissionTypeDTO transmissionTypeDTO) {
        TransmissionTypeDTO createdTransmissionType = transmissionTypeService
                .createTransmissionType(transmissionTypeDTO);
        return new ResponseEntity<>(createdTransmissionType, HttpStatus.CREATED);
    }

    /**
     * Get a transmission type by its ID
     * 
     * @param transmissionTypeId the ID of the transmission type
     * @return ResponseEntity with the transmission type DTO if found, otherwise 404
     *         Not Found
     */
    @GetMapping("/{transmissionTypeId}")
    public ResponseEntity<TransmissionTypeDTO> getTransmissionTypeById(@PathVariable UUID transmissionTypeId) {
        return transmissionTypeService.getTransmissionTypeById(transmissionTypeId)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all transmission types
     * 
     * @return ResponseEntity with list of all transmission type DTOs and HTTP 200
     *         OK status
     */
    @GetMapping
    public ResponseEntity<List<TransmissionTypeDTO>> getAllTransmissionTypes() {
        List<TransmissionTypeDTO> transmissionTypes = transmissionTypeService.getAllTransmissionTypes();
        return new ResponseEntity<>(transmissionTypes, HttpStatus.OK);
    }

    /**
     * Get all transmission types for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of transmission type DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<TransmissionTypeDTO>> getTransmissionTypesByAgency(@PathVariable String agencyId) {
        List<TransmissionTypeDTO> transmissionTypes = transmissionTypeService.getTransmissionTypesByAgency(agencyId);
        return new ResponseEntity<>(transmissionTypes, HttpStatus.OK);
    }

    /**
     * Get a transmission type by its name
     * 
     * @param typeName the name of the transmission type
     * @return ResponseEntity with the transmission type DTO if found, otherwise 404
     *         Not Found
     */
    @GetMapping("/name/{typeName}")
    public ResponseEntity<TransmissionTypeDTO> getTransmissionTypeByName(@PathVariable String typeName) {
        return transmissionTypeService.getTransmissionTypeByName(typeName)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing transmission type
     * 
     * @param transmissionTypeId  the ID of the transmission type to update
     * @param transmissionTypeDTO the updated transmission type data
     * @return ResponseEntity with the updated transmission type DTO if successful,
     *         otherwise 404 Not Found
     */
    @PutMapping("/{transmissionTypeId}")
    public ResponseEntity<TransmissionTypeDTO> updateTransmissionType(@PathVariable UUID transmissionTypeId,
            @RequestBody TransmissionTypeDTO transmissionTypeDTO) {
        return transmissionTypeService.updateTransmissionType(transmissionTypeId, transmissionTypeDTO)
                .map(type -> new ResponseEntity<>(type, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a transmission type by its ID
     * 
     * @param transmissionTypeId the ID of the transmission type to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{transmissionTypeId}")
    public ResponseEntity<Void> deleteTransmissionType(@PathVariable UUID transmissionTypeId) {
        if (transmissionTypeService.deleteTransmissionType(transmissionTypeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
