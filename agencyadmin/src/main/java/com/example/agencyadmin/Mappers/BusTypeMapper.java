package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.BusTypeDTO;
import com.example.agencyadmin.Models.BusType;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between BusType entity and BusTypeDTO.
 * This mapper is responsible for converting BusType JPA entities to DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class BusTypeMapper {
    
    /**
     * Converts a BusType entity to BusTypeDTO
     * @param busType the BusType entity
     * @return BusTypeDTO containing the data from the BusType entity
     */
    public BusTypeDTO toDTO(BusType busType) {
        if (busType == null) {
            return null;
        }
        return new BusTypeDTO(
            busType.getBusTypeId(),
            busType.getBusTypeName()
        );
    }
    
    /**
     * Converts a BusTypeDTO to BusType entity
     * @param busTypeDTO the BusTypeDTO
     * @return BusType entity containing the data from the DTO
     */
    public BusType toEntity(BusTypeDTO busTypeDTO) {
        if (busTypeDTO == null) {
            return null;
        }
        BusType busType = new BusType();
        busType.setBusTypeName(busTypeDTO.getBusTypeName());
        return busType;
    }
}

