package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.TransmissionTypeDTO;
import com.example.agencyadmin.Models.TransmissionType;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between TransmissionType entity and
 * TransmissionTypeDTO.
 * This mapper is responsible for converting TransmissionType JPA entities to
 * DTOs and vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class TransmissionTypeMapper {

    /**
     * Converts a TransmissionType entity to TransmissionTypeDTO
     * 
     * @param transmissionType the TransmissionType entity
     * @return TransmissionTypeDTO containing the data from the TransmissionType
     *         entity
     */
    public TransmissionTypeDTO toDTO(TransmissionType transmissionType) {
        if (transmissionType == null) {
            return null;
        }
        return new TransmissionTypeDTO(
                transmissionType.getTransmissionTypeId(),
                transmissionType.getTypeName(),
                transmissionType.getAgencyid());
    }

    /**
     * Converts a TransmissionTypeDTO to TransmissionType entity
     * 
     * @param transmissionTypeDTO the TransmissionTypeDTO
     * @return TransmissionType entity containing the data from the DTO
     */
    public TransmissionType toEntity(TransmissionTypeDTO transmissionTypeDTO) {
        if (transmissionTypeDTO == null) {
            return null;
        }
        TransmissionType transmissionType = new TransmissionType();
        transmissionType.setTypeName(transmissionTypeDTO.getTypeName());
        transmissionType.setAgencyid(transmissionTypeDTO.getAgencyId());
        return transmissionType;
    }
}
