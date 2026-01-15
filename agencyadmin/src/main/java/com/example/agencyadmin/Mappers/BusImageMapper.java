package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.BusImageDTO;
import com.example.agencyadmin.Models.BusImage;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between BusImage entity and BusImageDTO.
 * This mapper is responsible for converting BusImage JPA entities to DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class BusImageMapper {
    
    /**
     * Converts a BusImage entity to BusImageDTO
     * @param busImage the BusImage entity
     * @return BusImageDTO containing the data from the BusImage entity
     */
    public BusImageDTO toDTO(BusImage busImage) {
        if (busImage == null) {
            return null;
        }
        return new BusImageDTO(
            busImage.getImageId(),
            busImage.getBusId(),
            busImage.getS3BucketName(),
            busImage.getS3Key(),
            busImage.getImageUrl(),
            busImage.getFileName(),
            busImage.getContentType(),
            busImage.getFileSize(),
            busImage.getIsPrimary(),
            busImage.getUploadedAt(),
            busImage.getDescription()
        );
    }
    
    /**
     * Converts a BusImageDTO to BusImage entity
     * @param busImageDTO the BusImageDTO
     * @return BusImage entity containing the data from the DTO
     */
    public BusImage toEntity(BusImageDTO busImageDTO) {
        if (busImageDTO == null) {
            return null;
        }
        BusImage busImage = new BusImage();
        busImage.setBusId(busImageDTO.getBusId());
        busImage.setS3BucketName(busImageDTO.getS3BucketName());
        busImage.setS3Key(busImageDTO.getS3Key());
        busImage.setImageUrl(busImageDTO.getImageUrl());
        busImage.setFileName(busImageDTO.getFileName());
        busImage.setContentType(busImageDTO.getContentType());
        busImage.setFileSize(busImageDTO.getFileSize());
        busImage.setIsPrimary(busImageDTO.getIsPrimary());
        busImage.setDescription(busImageDTO.getDescription());
        return busImage;
    }
}

