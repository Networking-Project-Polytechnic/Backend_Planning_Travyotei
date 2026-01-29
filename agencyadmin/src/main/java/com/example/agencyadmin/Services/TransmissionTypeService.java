package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.TransmissionTypeDTO;
import com.example.agencyadmin.Models.TransmissionType;
import com.example.agencyadmin.Repositories.TransmissionTypeRepository;
import com.example.agencyadmin.Mappers.TransmissionTypeMapper;

/**
 * Service class for TransmissionType entity.
 * This service encapsulates business logic for TransmissionType operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to transmission types should be implemented in
 * this service.
 */
@Service
public class TransmissionTypeService {

    /** The TransmissionType repository for database operations */
    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    /** The TransmissionType mapper for converting between entities and DTOs */
    @Autowired
    private TransmissionTypeMapper transmissionTypeMapper;

    /**
     * Create a new transmission type
     * 
     * @param transmissionTypeDTO the transmission type data transfer object
     * @return the created transmission type DTO
     */
    public TransmissionTypeDTO createTransmissionType(TransmissionTypeDTO transmissionTypeDTO) {
        TransmissionType transmissionType = transmissionTypeMapper.toEntity(transmissionTypeDTO);
        TransmissionType savedTransmissionType = transmissionTypeRepository.save(transmissionType);
        return transmissionTypeMapper.toDTO(savedTransmissionType);
    }

    /**
     * Get a transmission type by its ID
     * 
     * @param transmissionTypeId the ID of the transmission type
     * @return the transmission type DTO if found
     */
    public Optional<TransmissionTypeDTO> getTransmissionTypeById(UUID transmissionTypeId) {
        Optional<TransmissionType> transmissionType = transmissionTypeRepository.findById(transmissionTypeId);
        return transmissionType.map(transmissionTypeMapper::toDTO);
    }

    /**
     * Get all transmission types
     * 
     * @return list of all transmission type DTOs
     */
    public List<TransmissionTypeDTO> getAllTransmissionTypes() {
        List<TransmissionType> transmissionTypes = transmissionTypeRepository.findAll();
        return transmissionTypes.stream().map(transmissionTypeMapper::toDTO).toList();
    }

    public List<TransmissionTypeDTO> getTransmissionTypesByAgency(String agencyId) {
        List<TransmissionType> transmissionTypes = transmissionTypeRepository.findByAgencyid(agencyId);
        return transmissionTypes.stream().map(transmissionTypeMapper::toDTO).toList();
    }

    /**
     * Get a transmission type by its name
     * 
     * @param typeName the name of the transmission type
     * @return the transmission type DTO if found
     */
    public Optional<TransmissionTypeDTO> getTransmissionTypeByName(String typeName) {
        Optional<TransmissionType> transmissionType = transmissionTypeRepository.findByTypeName(typeName);
        return transmissionType.map(transmissionTypeMapper::toDTO);
    }

    /**
     * Update an existing transmission type
     * 
     * @param transmissionTypeId  the ID of the transmission type to update
     * @param transmissionTypeDTO the updated transmission type data
     * @return the updated transmission type DTO
     */
    public Optional<TransmissionTypeDTO> updateTransmissionType(UUID transmissionTypeId,
            TransmissionTypeDTO transmissionTypeDTO) {
        Optional<TransmissionType> existingTransmissionType = transmissionTypeRepository.findById(transmissionTypeId);
        if (existingTransmissionType.isPresent()) {
            TransmissionType transmissionType = existingTransmissionType.get();
            transmissionType.setTypeName(transmissionTypeDTO.getTypeName());
            transmissionType.setAgencyid(transmissionTypeDTO.getAgencyId());
            TransmissionType updatedTransmissionType = transmissionTypeRepository.save(transmissionType);
            return Optional.of(transmissionTypeMapper.toDTO(updatedTransmissionType));
        }
        return Optional.empty();
    }

    /**
     * Delete a transmission type by its ID
     * 
     * @param transmissionTypeId the ID of the transmission type to delete
     * @return true if deletion was successful
     */
    public boolean deleteTransmissionType(UUID transmissionTypeId) {
        if (transmissionTypeRepository.existsById(transmissionTypeId)) {
            transmissionTypeRepository.deleteById(transmissionTypeId);
            return true;
        }
        return false;
    }
}
