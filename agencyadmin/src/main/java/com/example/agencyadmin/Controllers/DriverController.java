package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.DriverDTO;
import com.example.agencyadmin.Services.DriverService;

/**
 * REST Controller for Driver entity.
 * Handles HTTP requests related to driver operations.
 * Provides endpoints for CRUD operations and driver queries.
 * 
 * Base path: /api/v1/drivers
 */
@RestController
@RequestMapping("/api/v1/drivers")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DriverController {

    /** The Driver service for business logic */
    @Autowired
    private DriverService driverService;

    /**
     * Create a new driver
     * 
     * @param driverDTO the driver data transfer object
     * @return ResponseEntity with the created driver DTO and HTTP 201 Created
     *         status
     */
    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO) {
        DriverDTO createdDriver = driverService.createDriver(driverDTO);
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    /**
     * Create a new driver for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param driverDTO the driver data
     * @return ResponseEntity with the created driver DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<DriverDTO> createDriverScoped(@PathVariable String agencyId,
            @RequestBody DriverDTO driverDTO) {
        DriverDTO createdDriver = driverService.createDriverScoped(agencyId, driverDTO);
        return new ResponseEntity<>(createdDriver, HttpStatus.CREATED);
    }

    /**
     * Get a driver by its ID
     * 
     * @param driverId the ID of the driver
     * @return ResponseEntity with the driver DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable UUID driverId) {
        return driverService.getDriverById(driverId)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all drivers
     * 
     * @return ResponseEntity with list of all driver DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<DriverDTO> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    /**
     * Get all drivers for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of driver DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<DriverDTO>> getDriversByAgency(@PathVariable String agencyId) {
        List<DriverDTO> drivers = driverService.getDriversByAgency(agencyId);
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    /**
     * Get a driver by their license number
     * 
     * @param licenseNumber the license number
     * @return ResponseEntity with the driver DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<DriverDTO> getDriverByLicenseNumber(@PathVariable String licenseNumber) {
        return driverService.getDriverByLicenseNumber(licenseNumber)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get a driver by their full name
     * 
     * @param fullName the full name of the driver
     * @return ResponseEntity with the driver DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/name/{fullName}")
    public ResponseEntity<DriverDTO> getDriverByFullName(@PathVariable String fullName) {
        return driverService.getDriverByFullName(fullName)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing driver
     * 
     * @param driverId  the ID of the driver to update
     * @param driverDTO the updated driver data
     * @return ResponseEntity with the updated driver DTO if successful, otherwise
     *         404 Not Found
     */
    @PutMapping("/{driverId}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable UUID driverId, @RequestBody DriverDTO driverDTO) {
        return driverService.updateDriver(driverId, driverDTO)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing driver for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param driverId  the ID of the driver to update
     * @param driverDTO the updated driver data
     * @return ResponseEntity with the updated driver DTO
     */
    @PutMapping("/agency/{agencyId}/{driverId}")
    public ResponseEntity<DriverDTO> updateDriverScoped(@PathVariable String agencyId, @PathVariable UUID driverId,
            @RequestBody DriverDTO driverDTO) {
        return driverService.updateDriverScoped(agencyId, driverId, driverDTO)
                .map(driver -> new ResponseEntity<>(driver, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a driver by its ID
     * 
     * @param driverId the ID of the driver to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable UUID driverId) {
        if (driverService.deleteDriver(driverId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a driver for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param driverId the ID of the driver to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{driverId}")
    public ResponseEntity<Void> deleteDriverScoped(@PathVariable String agencyId, @PathVariable UUID driverId) {
        if (driverService.deleteDriverScoped(agencyId, driverId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
