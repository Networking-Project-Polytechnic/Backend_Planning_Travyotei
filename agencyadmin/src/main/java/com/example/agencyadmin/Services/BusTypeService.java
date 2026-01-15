package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.BusTypeDTO;
import com.example.agencyadmin.Models.BusType;
import com.example.agencyadmin.Repositories.BusTypeRepository;
import com.example.agencyadmin.Mappers.BusTypeMapper;

/**
 * Service class for BusType entity.
 * This service encapsulates business logic for BusType operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to bus types should be implemented in this service.
 */
@Service
public class BusTypeService {
    
    /** The BusType repository for database operations */
    @Autowired
    private BusTypeRepository busTypeRepository;
    
    /** The BusType mapper for converting between entities and DTOs */
    @Autowired
    private BusTypeMapper busTypeMapper;
    
    /**
     * Create a new bus type
     * @param busTypeDTO the bus type data transfer object
     * @return the created bus type DTO
     */
    public BusTypeDTO createBusType(BusTypeDTO busTypeDTO) {
        BusType busType = busTypeMapper.toEntity(busTypeDTO);
        BusType savedBusType = busTypeRepository.save(busType);
        return busTypeMapper.toDTO(savedBusType);
    }
    
    /**
     * Get a bus type by its ID
     * @param busTypeId the ID of the bus type
     * @return the bus type DTO if found
     */
    public Optional<BusTypeDTO> getBusTypeById(UUID busTypeId) {
        Optional<BusType> busType = busTypeRepository.findById(busTypeId);
        return busType.map(busTypeMapper::toDTO);
    }
    
    /**
     * Get all bus types
     * @return list of all bus type DTOs
     */
    public List<BusTypeDTO> getAllBusTypes() {
        List<BusType> busTypes = busTypeRepository.findAll();
        return busTypes.stream().map(busTypeMapper::toDTO).toList();
    }
    
    /**
     * Get a bus type by its name
     * @param busTypeName the name of the bus type
     * @return the bus type DTO if found
     */
    public Optional<BusTypeDTO> getBusTypeByName(String busTypeName) {
        Optional<BusType> busType = busTypeRepository.findByBusTypeName(busTypeName);
        return busType.map(busTypeMapper::toDTO);
    }
    
    /**
     * Update an existing bus type
     * @param busTypeId the ID of the bus type to update
     * @param busTypeDTO the updated bus type data
     * @return the updated bus type DTO
     */
    public Optional<BusTypeDTO> updateBusType(UUID busTypeId, BusTypeDTO busTypeDTO) {
        Optional<BusType> existingBusType = busTypeRepository.findById(busTypeId);
        if (existingBusType.isPresent()) {
            BusType busType = existingBusType.get();
            busType.setBusTypeName(busTypeDTO.getBusTypeName());
            BusType updatedBusType = busTypeRepository.save(busType);
            return Optional.of(busTypeMapper.toDTO(updatedBusType));
        }
        return Optional.empty();
    }
    
    /**
     * Delete a bus type by its ID
     * @param busTypeId the ID of the bus type to delete
     * @return true if deletion was successful
     */
    public boolean deleteBusType(UUID busTypeId) {
        if (busTypeRepository.existsById(busTypeId)) {
            busTypeRepository.deleteById(busTypeId);
            return true;
        }
        return false;
    }
}

