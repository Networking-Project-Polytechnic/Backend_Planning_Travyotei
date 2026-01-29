package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.BusModelDTO;
import com.example.agencyadmin.Models.BusModels;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between BusModels entity and BusModelDTO.
 * This mapper is responsible for converting BusModels JPA entities to DTOs and
 * vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class BusModelMapper {

    /**
     * Converts a BusModels entity to BusModelDTO
     * 
     * @param busModel the BusModels entity
     * @return BusModelDTO containing the data from the BusModels entity
     */
    public BusModelDTO toDTO(BusModels busModel) {
        if (busModel == null) {
            return null;
        }
        return new BusModelDTO(
                busModel.getBusModelId(),
                busModel.getModelName(),
                busModel.getAgencyid());
    }

    /**
     * Converts a BusModelDTO to BusModels entity
     * 
     * @param busModelDTO the BusModelDTO
     * @return BusModels entity containing the data from the DTO
     */
    public BusModels toEntity(BusModelDTO busModelDTO) {
        if (busModelDTO == null) {
            return null;
        }
        BusModels busModel = new BusModels();
        busModel.setModelName(busModelDTO.getModelName());
        busModel.setAgencyid(busModelDTO.getAgencyId());
        return busModel;
    }
}
