package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.BusImageDTO;
import com.example.agencyadmin.Services.BusImageService;

/**
 * REST Controller for BusImage entity.
 * Handles HTTP requests related to bus image operations.
 * Provides endpoints for CRUD operations and bus image queries.
 * 
 * Base path: /api/v1/bus-images
 */
@RestController
@RequestMapping("/api/v1/bus-images")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BusImageController {
    
    /** The BusImage service for business logic */
    @Autowired
    private BusImageService busImageService;
    
    /**
     * Create a new bus image
     * @param busImageDTO the bus image data transfer object
     * @return ResponseEntity with the created bus image DTO and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<BusImageDTO> createBusImage(@RequestBody BusImageDTO busImageDTO) {
        BusImageDTO createdBusImage = busImageService.createBusImage(busImageDTO);
        return new ResponseEntity<>(createdBusImage, HttpStatus.CREATED);
    }
    
    /**
     * Get a bus image by its ID
     * @param imageId the ID of the bus image
     * @return ResponseEntity with the bus image DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/{imageId}")
    public ResponseEntity<BusImageDTO> getBusImageById(@PathVariable UUID imageId) {
        return busImageService.getBusImageById(imageId)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Get all bus images
     * @return ResponseEntity with list of all bus image DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<BusImageDTO>> getAllBusImages() {
        List<BusImageDTO> busImages = busImageService.getAllBusImages();
        return new ResponseEntity<>(busImages, HttpStatus.OK);
    }
    
    /**
     * Get all images for a specific bus
     * @param busId the ID of the bus
     * @return ResponseEntity with list of bus image DTOs for the bus
     */
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<BusImageDTO>> getBusImagesByBusId(@PathVariable UUID busId) {
        List<BusImageDTO> busImages = busImageService.getBusImagesByBusId(busId);
        return new ResponseEntity<>(busImages, HttpStatus.OK);
    }
    
    /**
     * Get a bus image by its S3 key
     * @param s3Key the S3 key of the image
     * @return ResponseEntity with the bus image DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/s3-key/{s3Key}")
    public ResponseEntity<BusImageDTO> getBusImageByS3Key(@PathVariable String s3Key) {
        return busImageService.getBusImageByS3Key(s3Key)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Get the primary image for a bus
     * @param busId the ID of the bus
     * @return ResponseEntity with the primary bus image DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/bus/{busId}/primary")
    public ResponseEntity<BusImageDTO> getPrimaryBusImage(@PathVariable UUID busId) {
        return busImageService.getPrimaryBusImage(busId)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Get all primary or non-primary images for a bus
     * @param busId the ID of the bus
     * @param isPrimary whether to get primary (true) or non-primary (false) images
     * @return ResponseEntity with list of bus image DTOs
     */
    @GetMapping("/bus/{busId}/primary/{isPrimary}")
    public ResponseEntity<List<BusImageDTO>> getBusImagesByBusIdAndIsPrimary(
            @PathVariable UUID busId, @PathVariable Boolean isPrimary) {
        List<BusImageDTO> busImages = busImageService.getBusImagesByBusIdAndIsPrimary(busId, isPrimary);
        return new ResponseEntity<>(busImages, HttpStatus.OK);
    }
    
    /**
     * Update an existing bus image
     * @param imageId the ID of the bus image to update
     * @param busImageDTO the updated bus image data
     * @return ResponseEntity with the updated bus image DTO if successful, otherwise 404 Not Found
     */
    @PutMapping("/{imageId}")
    public ResponseEntity<BusImageDTO> updateBusImage(@PathVariable UUID imageId, @RequestBody BusImageDTO busImageDTO) {
        return busImageService.updateBusImage(imageId, busImageDTO)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Delete a bus image by its ID
     * @param imageId the ID of the bus image to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404 Not Found
     */
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteBusImage(@PathVariable UUID imageId) {
        if (busImageService.deleteBusImage(imageId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    /**
     * Delete all images for a specific bus
     * @param busId the ID of the bus
     * @return ResponseEntity with the count of deleted images
     */
    @DeleteMapping("/bus/{busId}")
    public ResponseEntity<Integer> deleteAllBusImagesByBusId(@PathVariable UUID busId) {
        int deletedCount = busImageService.deleteAllBusImagesByBusId(busId);
        return new ResponseEntity<>(deletedCount, HttpStatus.OK);
    }
}

