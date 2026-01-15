package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.DriverImageDTO;
import com.example.agencyadmin.Models.DriverImage;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between DriverImage entity and DriverImageDTO.
 * This mapper is responsible for converting DriverImage JPA entities to DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class DriverImageMapper {
    
    /**
     * Converts a DriverImage entity to DriverImageDTO
     * @param driverImage the DriverImage entity
     * @return DriverImageDTO containing the data from the DriverImage entity
     */
    public DriverImageDTO toDTO(DriverImage driverImage) {
        if (driverImage == null) {
            return null;
        }
        return new DriverImageDTO(
            driverImage.getImageId(),
            driverImage.getDriverId(),
            driverImage.getS3BucketName(),
            driverImage.getS3Key(),
            driverImage.getImageUrl(),
            driverImage.getFileName(),
            driverImage.getContentType(),
            driverImage.getFileSize(),
            driverImage.getIsPrimary(),
            driverImage.getUploadedAt(),
            driverImage.getDescription()
        );
    }
    
    /**
     * Converts a DriverImageDTO to DriverImage entity
     * @param driverImageDTO the DriverImageDTO
     * @return DriverImage entity containing the data from the DTO
     */
    public DriverImage toEntity(DriverImageDTO driverImageDTO) {
        if (driverImageDTO == null) {
            return null;
        }
        DriverImage driverImage = new DriverImage();
        driverImage.setDriverId(driverImageDTO.getDriverId());
        driverImage.setS3BucketName(driverImageDTO.getS3BucketName());
        driverImage.setS3Key(driverImageDTO.getS3Key());
        driverImage.setImageUrl(driverImageDTO.getImageUrl());
        driverImage.setFileName(driverImageDTO.getFileName());
        driverImage.setContentType(driverImageDTO.getContentType());
        driverImage.setFileSize(driverImageDTO.getFileSize());
        driverImage.setIsPrimary(driverImageDTO.getIsPrimary());
        driverImage.setDescription(driverImageDTO.getDescription());
        return driverImage;
    }
}

