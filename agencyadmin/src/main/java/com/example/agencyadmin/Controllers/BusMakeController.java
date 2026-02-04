package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.BusMakeDTO;
import com.example.agencyadmin.Services.BusMakeService;

/**
 * REST Controller for BusMakes entity.
 * Handles HTTP requests related to bus make operations.
 * Provides endpoints for CRUD operations and bus make queries.
 * 
 * Base path: /api/v1/bus-makes
 */
@RestController
@RequestMapping("/api/v1/bus-makes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusMakeController {

    /** The BusMake service for business logic */
    @Autowired
    private BusMakeService busMakeService;

    /**
     * Create a new bus make
     * 
     * @param busMakeDTO the bus make data transfer object
     * @return ResponseEntity with the created bus make DTO and HTTP 201 Created
     *         status
     */
    @PostMapping
    public ResponseEntity<BusMakeDTO> createBusMake(@RequestBody BusMakeDTO busMakeDTO) {
        BusMakeDTO createdBusMake = busMakeService.createBusMake(busMakeDTO);
        return new ResponseEntity<>(createdBusMake, HttpStatus.CREATED);
    }

    /**
     * Create a new bus make for a specific agency
     * 
     * @param agencyId   the ID of the agency
     * @param busMakeDTO the bus make data
     * @return ResponseEntity with the created bus make DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<BusMakeDTO> createBusMakeScoped(@PathVariable String agencyId,
            @RequestBody BusMakeDTO busMakeDTO) {
        BusMakeDTO createdBusMake = busMakeService.createBusMakeScoped(agencyId, busMakeDTO);
        return new ResponseEntity<>(createdBusMake, HttpStatus.CREATED);
    }

    /**
     * Get a bus make by its ID
     * 
     * @param busMakeId the ID of the bus make
     * @return ResponseEntity with the bus make DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/{busMakeId}")
    public ResponseEntity<BusMakeDTO> getBusMakeById(@PathVariable UUID busMakeId) {
        return busMakeService.getBusMakeById(busMakeId)
                .map(make -> new ResponseEntity<>(make, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all bus makes
     * 
     * @return ResponseEntity with list of all bus make DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<BusMakeDTO>> getAllBusMakes() {
        List<BusMakeDTO> busMakes = busMakeService.getAllBusMakes();
        return new ResponseEntity<>(busMakes, HttpStatus.OK);
    }

    /**
     * Get all bus makes for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of bus make DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<BusMakeDTO>> getBusMakesByAgency(@PathVariable String agencyId) {
        List<BusMakeDTO> busMakes = busMakeService.getBusMakesByAgency(agencyId);
        return new ResponseEntity<>(busMakes, HttpStatus.OK);
    }

    /**
     * Get a bus make by its name
     * 
     * @param makeName the name of the bus make
     * @return ResponseEntity with the bus make DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/name/{makeName}")
    public ResponseEntity<BusMakeDTO> getBusMakeByName(@PathVariable String makeName) {
        return busMakeService.getBusMakeByName(makeName)
                .map(make -> new ResponseEntity<>(make, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus make
     * 
     * @param busMakeId  the ID of the bus make to update
     * @param busMakeDTO the updated bus make data
     * @return ResponseEntity with the updated bus make DTO if successful, otherwise
     *         404 Not Found
     */
    @PutMapping("/{busMakeId}")
    public ResponseEntity<BusMakeDTO> updateBusMake(@PathVariable UUID busMakeId, @RequestBody BusMakeDTO busMakeDTO) {
        return busMakeService.updateBusMake(busMakeId, busMakeDTO)
                .map(make -> new ResponseEntity<>(make, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing bus make for a specific agency
     * 
     * @param agencyId   the ID of the agency
     * @param busMakeId  the ID of the bus make to update
     * @param busMakeDTO the updated bus make data
     * @return ResponseEntity with the updated bus make DTO if successful
     */
    @PutMapping("/agency/{agencyId}/{busMakeId}")
    public ResponseEntity<BusMakeDTO> updateBusMakeScoped(@PathVariable String agencyId, @PathVariable UUID busMakeId,
            @RequestBody BusMakeDTO busMakeDTO) {
        return busMakeService.updateBusMakeScoped(agencyId, busMakeId, busMakeDTO)
                .map(make -> new ResponseEntity<>(make, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a bus make by its ID
     * 
     * @param busMakeId the ID of the bus make to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{busMakeId}")
    public ResponseEntity<Void> deleteBusMake(@PathVariable UUID busMakeId) {
        if (busMakeService.deleteBusMake(busMakeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a bus make for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param busMakeId the ID of the bus make to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{busMakeId}")
    public ResponseEntity<Void> deleteBusMakeScoped(@PathVariable String agencyId, @PathVariable UUID busMakeId) {
        if (busMakeService.deleteBusMakeScoped(agencyId, busMakeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
