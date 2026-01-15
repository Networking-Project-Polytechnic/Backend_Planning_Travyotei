package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.BusManufacturerDTO;
import com.example.agencyadmin.Models.BusManufacturers;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between BusManufacturers entity and BusManufacturerDTO.
 * This mapper is responsible for converting BusManufacturers JPA entities to DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class BusManufacturerMapper {
    
    /**
     * Converts a BusManufacturers entity to BusManufacturerDTO
     * @param busManufacturer the BusManufacturers entity
     * @return BusManufacturerDTO containing the data from the BusManufacturers entity
     */
    public BusManufacturerDTO toDTO(BusManufacturers busManufacturer) {
        if (busManufacturer == null) {
            return null;
        }
        return new BusManufacturerDTO(
            busManufacturer.getManufacturerId(),
            busManufacturer.getManufacturerName()
        );
    }
    
    /**
     * Converts a BusManufacturerDTO to BusManufacturers entity
     * @param busManufacturerDTO the BusManufacturerDTO
     * @return BusManufacturers entity containing the data from the DTO
     */
    public BusManufacturers toEntity(BusManufacturerDTO busManufacturerDTO) {
        if (busManufacturerDTO == null) {
            return null;
        }
        BusManufacturers busManufacturer = new BusManufacturers();
        busManufacturer.setManufacturerName(busManufacturerDTO.getManufacturerName());
        return busManufacturer;
    }
}

