package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.DriverImageDTO;
import com.example.agencyadmin.Services.DriverImageService;

/**
 * REST Controller for DriverImage entity.
 * Handles HTTP requests related to driver image operations.
 * Provides endpoints for CRUD operations and driver image queries.
 * 
 * Base path: /api/v1/driver-images
 */
@RestController
@RequestMapping("/api/v1/driver-images")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DriverImageController {

    /** The DriverImage service for business logic */
    @Autowired
    private DriverImageService driverImageService;

    /**
     * Create a new driver image
     * 
     * @param driverImageDTO the driver image data transfer object
     * @return ResponseEntity with the created driver image DTO and HTTP 201 Created
     *         status
     */
    /**
     * Create a new driver image.
     * Use this endpoint when the image has already been uploaded to Cloudinary
     * and you need to save the metadata.
     * 
     * @param driverImageDTO the driver image data transfer object
     * @return ResponseEntity with the created driver image DTO and HTTP 201 Created
     *         status
     */
    @PostMapping
    public ResponseEntity<DriverImageDTO> createDriverImage(@RequestBody DriverImageDTO driverImageDTO) {
        DriverImageDTO createdDriverImage = driverImageService.createDriverImage(driverImageDTO);
        return new ResponseEntity<>(createdDriverImage, HttpStatus.CREATED);
    }

    /**
     * Get a driver image by its ID
     * 
     * @param imageId the ID of the driver image
     * @return ResponseEntity with the driver image DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/{imageId}")
    public ResponseEntity<DriverImageDTO> getDriverImageById(@PathVariable UUID imageId) {
        return driverImageService.getDriverImageById(imageId)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all driver images
     * 
     * @return ResponseEntity with list of all driver image DTOs and HTTP 200 OK
     *         status
     */
    @GetMapping
    public ResponseEntity<List<DriverImageDTO>> getAllDriverImages() {
        List<DriverImageDTO> driverImages = driverImageService.getAllDriverImages();
        return new ResponseEntity<>(driverImages, HttpStatus.OK);
    }

    /**
     * Get all images for a specific driver
     * 
     * @param driverId the ID of the driver
     * @return ResponseEntity with list of driver image DTOs for the driver
     */
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<DriverImageDTO>> getDriverImagesByDriverId(@PathVariable UUID driverId) {
        List<DriverImageDTO> driverImages = driverImageService.getDriverImagesByDriverId(driverId);
        return new ResponseEntity<>(driverImages, HttpStatus.OK);
    }

    /**
     * Get a driver image by its Public ID
     * 
     * @param publicId the public ID of the image
     * @return ResponseEntity with the driver image DTO and HTTP 200 OK status
     */
    @GetMapping("/public/{publicId}")
    public ResponseEntity<DriverImageDTO> getDriverImageByPublicId(@PathVariable String publicId) {
        return driverImageService.getDriverImageByPublicId(publicId)
                .map(driverImageDTO -> new ResponseEntity<>(driverImageDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get the primary image for a driver
     * 
     * @param driverId the ID of the driver
     * @return ResponseEntity with the primary driver image DTO if found, otherwise
     *         404 Not Found
     */
    @GetMapping("/driver/{driverId}/primary")
    public ResponseEntity<DriverImageDTO> getPrimaryDriverImage(@PathVariable UUID driverId) {
        return driverImageService.getPrimaryDriverImage(driverId)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all primary or non-primary images for a driver
     * 
     * @param driverId  the ID of the driver
     * @param isPrimary whether to get primary (true) or non-primary (false) images
     * @return ResponseEntity with list of driver image DTOs
     */
    @GetMapping("/driver/{driverId}/primary/{isPrimary}")
    public ResponseEntity<List<DriverImageDTO>> getDriverImagesByDriverIdAndIsPrimary(
            @PathVariable UUID driverId, @PathVariable Boolean isPrimary) {
        List<DriverImageDTO> driverImages = driverImageService.getDriverImagesByDriverIdAndIsPrimary(driverId,
                isPrimary);
        return new ResponseEntity<>(driverImages, HttpStatus.OK);
    }

    /**
     * Update an existing driver image
     * 
     * @param imageId        the ID of the driver image to update
     * @param driverImageDTO the updated driver image data
     * @return ResponseEntity with the updated driver image DTO if successful,
     *         otherwise 404 Not Found
     */
    @PutMapping("/{imageId}")
    public ResponseEntity<DriverImageDTO> updateDriverImage(@PathVariable UUID imageId,
            @RequestBody DriverImageDTO driverImageDTO) {
        return driverImageService.updateDriverImage(imageId, driverImageDTO)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a driver image by its ID
     * 
     * @param imageId the ID of the driver image to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteDriverImage(@PathVariable UUID imageId) {
        if (driverImageService.deleteDriverImage(imageId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete all images for a specific driver
     * 
     * @param driverId the ID of the driver
     * @return ResponseEntity with the count of deleted images
     */
    @DeleteMapping("/driver/{driverId}")
    public ResponseEntity<Integer> deleteAllDriverImagesByDriverId(@PathVariable UUID driverId) {
        int deletedCount = driverImageService.deleteAllDriverImagesByDriverId(driverId);
        return new ResponseEntity<>(deletedCount, HttpStatus.OK);
    }
}
