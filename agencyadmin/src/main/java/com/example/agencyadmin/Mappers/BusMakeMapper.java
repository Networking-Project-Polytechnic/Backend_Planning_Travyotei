package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.BusMakeDTO;
import com.example.agencyadmin.Models.BusMakes;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between BusMakes entity and BusMakeDTO.
 * This mapper is responsible for converting BusMakes JPA entities to DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class BusMakeMapper {
    
    /**
     * Converts a BusMakes entity to BusMakeDTO
     * @param busMake the BusMakes entity
     * @return BusMakeDTO containing the data from the BusMakes entity
     */
    public BusMakeDTO toDTO(BusMakes busMake) {
        if (busMake == null) {
            return null;
        }
        return new BusMakeDTO(
            busMake.getBusMakeId(),
            busMake.getMakeName()
        );
    }
    
    /**
     * Converts a BusMakeDTO to BusMakes entity
     * @param busMakeDTO the BusMakeDTO
     * @return BusMakes entity containing the data from the DTO
     */
    public BusMakes toEntity(BusMakeDTO busMakeDTO) {
        if (busMakeDTO == null) {
            return null;
        }
        BusMakes busMake = new BusMakes();
        busMake.setMakeName(busMakeDTO.getMakeName());
        return busMake;
    }
}

