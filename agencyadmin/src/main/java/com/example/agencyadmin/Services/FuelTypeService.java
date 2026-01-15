package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.FuelTypeDTO;
import com.example.agencyadmin.Models.FuelType;
import com.example.agencyadmin.Repositories.FuelTypeRepository;
import com.example.agencyadmin.Mappers.FuelTypeMapper;

/**
 * Service class for FuelType entity.
 * This service encapsulates business logic for FuelType operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to fuel types should be implemented in this service.
 */
@Service
public class FuelTypeService {
    
    /** The FuelType repository for database operations */
    @Autowired
    private FuelTypeRepository fuelTypeRepository;
    
    /** The FuelType mapper for converting between entities and DTOs */
    @Autowired
    private FuelTypeMapper fuelTypeMapper;
    
    /**
     * Create a new fuel type
     * @param fuelTypeDTO the fuel type data transfer object
     * @return the created fuel type DTO
     */
    public FuelTypeDTO createFuelType(FuelTypeDTO fuelTypeDTO) {
        FuelType fuelType = fuelTypeMapper.toEntity(fuelTypeDTO);
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        return fuelTypeMapper.toDTO(savedFuelType);
    }
    
    /**
     * Get a fuel type by its ID
     * @param fuelTypeId the ID of the fuel type
     * @return the fuel type DTO if found
     */
    public Optional<FuelTypeDTO> getFuelTypeById(UUID fuelTypeId) {
        Optional<FuelType> fuelType = fuelTypeRepository.findById(fuelTypeId);
        return fuelType.map(fuelTypeMapper::toDTO);
    }
    
    /**
     * Get all fuel types
     * @return list of all fuel type DTOs
     */
    public List<FuelTypeDTO> getAllFuelTypes() {
        List<FuelType> fuelTypes = fuelTypeRepository.findAll();
        return fuelTypes.stream().map(fuelTypeMapper::toDTO).toList();
    }
    
    /**
     * Get a fuel type by its name
     * @param fuelTypeName the name of the fuel type
     * @return the fuel type DTO if found
     */
    public Optional<FuelTypeDTO> getFuelTypeByName(String fuelTypeName) {
        Optional<FuelType> fuelType = fuelTypeRepository.findByFuelTypeName(fuelTypeName);
        return fuelType.map(fuelTypeMapper::toDTO);
    }
    
    /**
     * Update an existing fuel type
     * @param fuelTypeId the ID of the fuel type to update
     * @param fuelTypeDTO the updated fuel type data
     * @return the updated fuel type DTO
     */
    public Optional<FuelTypeDTO> updateFuelType(UUID fuelTypeId, FuelTypeDTO fuelTypeDTO) {
        Optional<FuelType> existingFuelType = fuelTypeRepository.findById(fuelTypeId);
        if (existingFuelType.isPresent()) {
            FuelType fuelType = existingFuelType.get();
            fuelType.setFuelTypeName(fuelTypeDTO.getFuelTypeName());
            FuelType updatedFuelType = fuelTypeRepository.save(fuelType);
            return Optional.of(fuelTypeMapper.toDTO(updatedFuelType));
        }
        return Optional.empty();
    }
    
    /**
     * Delete a fuel type by its ID
     * @param fuelTypeId the ID of the fuel type to delete
     * @return true if deletion was successful
     */
    public boolean deleteFuelType(UUID fuelTypeId) {
        if (fuelTypeRepository.existsById(fuelTypeId)) {
            fuelTypeRepository.deleteById(fuelTypeId);
            return true;
        }
        return false;
    }
}
