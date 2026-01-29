package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.DriverImageDTO;
import com.example.agencyadmin.Models.DriverImage;
import com.example.agencyadmin.Repositories.DriverImageRepository;
import com.example.agencyadmin.Mappers.DriverImageMapper;

/**
 * Service class for DriverImage entity.
 * This service encapsulates business logic for DriverImage operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to driver images should be implemented in this
 * service.
 */
@Service
public class DriverImageService {

    /** The DriverImage repository for database operations */
    @Autowired
    private DriverImageRepository driverImageRepository;

    /** The DriverImage mapper for converting between entities and DTOs */
    @Autowired
    private DriverImageMapper driverImageMapper;

    /**
     * Create a new driver image record (metadata only).
     * 
     * @param driverImageDTO the driver image DTO containing Cloudinary metadata
     * @return the created driver image DTO
     */
    public DriverImageDTO createDriverImage(DriverImageDTO driverImageDTO) {
        DriverImage driverImage = driverImageMapper.toEntity(driverImageDTO);
        // Ensure uploadedAt is set if missing
        if (driverImage.getUploadedAt() == null) {
            driverImage.setUploadedAt(java.time.LocalDateTime.now());
        }
        DriverImage savedDriverImage = driverImageRepository.save(driverImage);
        return driverImageMapper.toDTO(savedDriverImage);
    }

    /**
     * Get a driver image by its ID
     * 
     * @param imageId the ID of the driver image
     * @return the driver image DTO if found
     */
    public Optional<DriverImageDTO> getDriverImageById(UUID imageId) {
        Optional<DriverImage> driverImage = driverImageRepository.findById(imageId);
        return driverImage.map(driverImageMapper::toDTO);
    }

    /**
     * Get all driver images
     * 
     * @return list of all driver image DTOs
     */
    public List<DriverImageDTO> getAllDriverImages() {
        List<DriverImage> driverImages = driverImageRepository.findAll();
        return driverImages.stream().map(driverImageMapper::toDTO).toList();
    }

    /**
     * Get all images for a specific driver
     * 
     * @param driverId the ID of the driver
     * @return list of driver image DTOs for the driver
     */
    public List<DriverImageDTO> getDriverImagesByDriverId(UUID driverId) {
        List<DriverImage> driverImages = driverImageRepository.findByDriverId(driverId);
        return driverImages.stream().map(driverImageMapper::toDTO).toList();
    }

    public List<DriverImageDTO> getDriverImagesByDriverIds(List<UUID> driverIds) {
        return driverImageRepository.findByDriverIdIn(driverIds).stream()
                .map(driverImageMapper::toDTO)
                .toList();
    }

    /** The Cloudinary Service for image operations */
    @Autowired
    private CloudinaryService cloudinaryService;

    /**
     * Get a driver image by its Public ID
     * 
     * @param publicId the Public ID of the image
     * @return the driver image DTO if found
     */
    public Optional<DriverImageDTO> getDriverImageByPublicId(String publicId) {
        Optional<DriverImage> driverImage = driverImageRepository.findByPublicId(publicId);
        return driverImage.map(driverImageMapper::toDTO);
    }

    /**
     * Get the primary image for a driver
     * 
     * @param driverId the ID of the driver
     * @return the primary driver image DTO if found
     */
    public Optional<DriverImageDTO> getPrimaryDriverImage(UUID driverId) {
        Optional<DriverImage> driverImage = driverImageRepository.findFirstByDriverIdAndIsPrimaryTrue(driverId);
        return driverImage.map(driverImageMapper::toDTO);
    }

    /**
     * Get all primary or non-primary images for a driver
     * 
     * @param driverId  the ID of the driver
     * @param isPrimary whether to get primary or non-primary images
     * @return list of driver image DTOs
     */
    public List<DriverImageDTO> getDriverImagesByDriverIdAndIsPrimary(UUID driverId, Boolean isPrimary) {
        List<DriverImage> driverImages = driverImageRepository.findByDriverIdAndIsPrimary(driverId, isPrimary);
        return driverImages.stream().map(driverImageMapper::toDTO).toList();
    }

    /**
     * Update an existing driver image
     * 
     * @param imageId        the ID of the driver image to update
     * @param driverImageDTO the updated driver image data
     * @return the updated driver image DTO
     */
    public Optional<DriverImageDTO> updateDriverImage(UUID imageId, DriverImageDTO driverImageDTO) {
        Optional<DriverImage> existingDriverImage = driverImageRepository.findById(imageId);
        if (existingDriverImage.isPresent()) {
            DriverImage driverImage = existingDriverImage.get();
            driverImage.setDriverId(driverImageDTO.getDriverId());
            driverImage.setPublicId(driverImageDTO.getPublicId());
            driverImage.setImageUrl(driverImageDTO.getImageUrl());
            driverImage.setFileName(driverImageDTO.getFileName());
            driverImage.setContentType(driverImageDTO.getContentType());
            driverImage.setFileSize(driverImageDTO.getFileSize());
            driverImage.setIsPrimary(driverImageDTO.getIsPrimary());
            driverImage.setDescription(driverImageDTO.getDescription());
            DriverImage updatedDriverImage = driverImageRepository.save(driverImage);
            return Optional.of(driverImageMapper.toDTO(updatedDriverImage));
        }
        return Optional.empty();
    }

    /**
     * Delete a driver image by its ID
     * 
     * @param imageId the ID of the driver image to delete
     * @return true if deletion was successful
     */
    public boolean deleteDriverImage(UUID imageId) {
        if (driverImageRepository.existsById(imageId)) {
            driverImageRepository.deleteById(imageId);
            return true;
        }
        return false;
    }

    /**
     * Delete all images for a specific driver
     * 
     * @param driverId the ID of the driver
     * @return the number of images deleted
     */
    public int deleteAllDriverImagesByDriverId(UUID driverId) {
        List<DriverImage> driverImages = driverImageRepository.findByDriverId(driverId);
        int count = driverImages.size();
        driverImageRepository.deleteAll(driverImages);
        return count;
    }
}
