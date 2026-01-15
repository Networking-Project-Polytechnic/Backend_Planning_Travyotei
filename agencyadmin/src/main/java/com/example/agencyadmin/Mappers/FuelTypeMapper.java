package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.FuelTypeDTO;
import com.example.agencyadmin.Models.FuelType;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between FuelType entity and FuelTypeDTO.
 * This mapper is responsible for converting FuelType JPA entities to DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class FuelTypeMapper {
    
    /**
     * Converts a FuelType entity to FuelTypeDTO
     * @param fuelType the FuelType entity
     * @return FuelTypeDTO containing the data from the FuelType entity
     */
    public FuelTypeDTO toDTO(FuelType fuelType) {
        if (fuelType == null) {
            return null;
        }
        return new FuelTypeDTO(
            fuelType.getFuelTypeId(),
            fuelType.getFuelTypeName()
        );
    }
    
    /**
     * Converts a FuelTypeDTO to FuelType entity
     * @param fuelTypeDTO the FuelTypeDTO
     * @return FuelType entity containing the data from the DTO
     */
    public FuelType toEntity(FuelTypeDTO fuelTypeDTO) {
        if (fuelTypeDTO == null) {
            return null;
        }
        FuelType fuelType = new FuelType();
        fuelType.setFuelTypeName(fuelTypeDTO.getFuelTypeName());
        return fuelType;
    }
}
