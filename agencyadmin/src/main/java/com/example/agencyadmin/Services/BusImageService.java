package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.BusImageDTO;
import com.example.agencyadmin.Models.BusImage;
import com.example.agencyadmin.Repositories.BusImageRepository;
import com.example.agencyadmin.Repositories.BusRepository;
import com.example.agencyadmin.Mappers.BusImageMapper;

/**
 * Service class for BusImage entity.
 * This service encapsulates business logic for BusImage operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to bus images should be implemented in this
 * service.
 */
@Service
public class BusImageService {

    /** The BusImage repository for database operations */
    @Autowired
    private BusImageRepository busImageRepository;

    /** The BusImage mapper for converting between entities and DTOs */
    @Autowired
    private BusImageMapper busImageMapper;

    @Autowired
    private BusRepository busRepository;

    /**
     * Create a new bus image record (metadata only).
     * 
     * @param busImageDTO the bus image DTO containing Cloudinary metadata
     * @return the created bus image DTO
     */
    public BusImageDTO createBusImage(BusImageDTO busImageDTO) {
        BusImage busImage = busImageMapper.toEntity(busImageDTO);
        // Ensure uploadedAt is set if missing
        if (busImage.getUploadedAt() == null) {
            busImage.setUploadedAt(java.time.LocalDateTime.now());
        }
        BusImage savedBusImage = busImageRepository.save(busImage);
        return busImageMapper.toDTO(savedBusImage);
    }

    /**
     * Create a new bus image for a specific agency.
     * Validates that the bus belongs to the agency.
     * 
     * @param agencyId    the ID of the agency
     * @param busImageDTO the bus image data
     * @return the created bus image DTO if successful
     */
    public Optional<BusImageDTO> createBusImageScoped(UUID agencyId, BusImageDTO busImageDTO) {
        return busRepository.findById(busImageDTO.getBusId())
                .filter(bus -> bus.getAgencyId().equals(agencyId))
                .map(bus -> createBusImage(busImageDTO));
    }

    /**
     * Get a bus image by its ID
     * 
     * @param imageId the ID of the bus image
     * @return the bus image DTO if found
     */
    public Optional<BusImageDTO> getBusImageById(UUID imageId) {
        Optional<BusImage> busImage = busImageRepository.findById(imageId);
        return busImage.map(busImageMapper::toDTO);
    }

    /**
     * Get all bus images
     * 
     * @return list of all bus image DTOs
     */
    public List<BusImageDTO> getAllBusImages() {
        List<BusImage> busImages = busImageRepository.findAll();
        return busImages.stream().map(busImageMapper::toDTO).toList();
    }

    /**
     * Get all images for a specific bus
     * 
     * @param busId the ID of the bus
     * @return list of bus image DTOs for the bus
     */
    public List<BusImageDTO> getBusImagesByBusId(UUID busId) {
        List<BusImage> busImages = busImageRepository.findByBusId(busId);
        return busImages.stream().map(busImageMapper::toDTO).toList();
    }

    public List<BusImageDTO> getBusImagesByBusIds(List<UUID> busIds) {
        return busImageRepository.findByBusIdIn(busIds).stream()
                .map(busImageMapper::toDTO)
                .toList();
    }

    

    /**
     * Get a bus image by its Public ID
     * 
     * @param publicId the public ID of the image
     * @return the bus image DTO if found
     */
    public Optional<BusImageDTO> getBusImageByPublicId(String publicId) {
        Optional<BusImage> busImage = busImageRepository.findByPublicId(publicId);
        return busImage.map(busImageMapper::toDTO);
    }

    /**
     * Get the primary image for a bus
     * 
     * @param busId the ID of the bus
     * @return the primary bus image DTO if found
     */
    public Optional<BusImageDTO> getPrimaryBusImage(UUID busId) {
        Optional<BusImage> busImage = busImageRepository.findFirstByBusIdAndIsPrimaryTrue(busId);
        return busImage.map(busImageMapper::toDTO);
    }

    /**
     * Get all primary or non-primary images for a bus
     * 
     * @param busId     the ID of the bus
     * @param isPrimary whether to get primary or non-primary images
     * @return list of bus image DTOs
     */
    public List<BusImageDTO> getBusImagesByBusIdAndIsPrimary(UUID busId, Boolean isPrimary) {
        List<BusImage> busImages = busImageRepository.findByBusIdAndIsPrimary(busId, isPrimary);
        return busImages.stream().map(busImageMapper::toDTO).toList();
    }

    /**
     * Update an existing bus image
     * 
     * @param imageId     the ID of the bus image to update
     * @param busImageDTO the updated bus image data
     * @return the updated bus image DTO
     */
    public Optional<BusImageDTO> updateBusImage(UUID imageId, BusImageDTO busImageDTO) {
        Optional<BusImage> existingBusImage = busImageRepository.findById(imageId);
        if (existingBusImage.isPresent()) {
            BusImage busImage = existingBusImage.get();
            busImage.setBusId(busImageDTO.getBusId());
            busImage.setPublicId(busImageDTO.getPublicId());
            busImage.setImageUrl(busImageDTO.getImageUrl());
            busImage.setFileName(busImageDTO.getFileName());
            busImage.setContentType(busImageDTO.getContentType());
            busImage.setFileSize(busImageDTO.getFileSize());
            busImage.setIsPrimary(busImageDTO.getIsPrimary());
            busImage.setDescription(busImageDTO.getDescription());
            BusImage updatedBusImage = busImageRepository.save(busImage);
            return Optional.of(busImageMapper.toDTO(updatedBusImage));
        }
        return Optional.empty();
    }

    /**
     * Delete a bus image by its ID
     * 
     * @param imageId the ID of the bus image to delete
     * @return true if deletion was successful
     */
    public boolean deleteBusImage(UUID imageId) {
        if (busImageRepository.existsById(imageId)) {
            busImageRepository.deleteById(imageId);
            return true;
        }
        return false;
    }

    /**
     * Delete a bus image for a specific agency.
     * Validates that the bus associated with the image belongs to the agency.
     * 
     * @param agencyId the ID of the agency
     * @param imageId  the ID of the bus image to delete
     * @return true if deletion was successful
     */
    public boolean deleteBusImageScoped(UUID agencyId, UUID imageId) {
        Optional<BusImage> existingBusImage = busImageRepository.findById(imageId);
        if (existingBusImage.isPresent()) {
            Optional<com.example.agencyadmin.Models.Bus> bus = busRepository
                    .findById(existingBusImage.get().getBusId());
            if (bus.isPresent() && bus.get().getAgencyId().equals(agencyId)) {
                return deleteBusImage(imageId);
            }
        }
        return false;
    }

    /**
     * Delete all images for a specific bus
     * 
     * @param busId the ID of the bus
     * @return the number of images deleted
     */
    public int deleteAllBusImagesByBusId(UUID busId) {
        List<BusImage> busImages = busImageRepository.findByBusId(busId);
        int count = busImages.size();
        busImageRepository.deleteAll(busImages);
        return count;
    }
}
