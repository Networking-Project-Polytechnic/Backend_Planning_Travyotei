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
 * All business logic related to fuel types should be implemented in this
 * service.
 */
@Service
public class FuelTypeService {

    /** The FuelType repository for database operations */
    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    /** The FuelType mapper for converting between entities and DTOs */
    @Autowired
    private FuelTypeMapper fuelTypeMapper;

    public FuelTypeDTO createFuelType(FuelTypeDTO fuelTypeDTO) {
        FuelType fuelType = fuelTypeMapper.toEntity(fuelTypeDTO);
        FuelType savedFuelType = fuelTypeRepository.save(fuelType);
        return fuelTypeMapper.toDTO(savedFuelType);
    }

    /**
     * Create a new fuel type for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param fuelTypeDTO the fuel type data
     * @return the created fuel type DTO
     */
    public FuelTypeDTO createFuelTypeScoped(String agencyId, FuelTypeDTO fuelTypeDTO) {
        fuelTypeDTO.setAgencyId(agencyId);
        return createFuelType(fuelTypeDTO);
    }

    /**
     * Get a fuel type by its ID
     * 
     * @param fuelTypeId the ID of the fuel type
     * @return the fuel type DTO if found
     */
    public Optional<FuelTypeDTO> getFuelTypeById(UUID fuelTypeId) {
        Optional<FuelType> fuelType = fuelTypeRepository.findById(fuelTypeId);
        return fuelType.map(fuelTypeMapper::toDTO);
    }

    /**
     * Get all fuel types
     * 
     * @return list of all fuel type DTOs
     */
    public List<FuelTypeDTO> getAllFuelTypes() {
        List<FuelType> fuelTypes = fuelTypeRepository.findAll();
        return fuelTypes.stream().map(fuelTypeMapper::toDTO).toList();
    }

    public List<FuelTypeDTO> getFuelTypesByAgency(String agencyId) {
        List<FuelType> fuelTypes = fuelTypeRepository.findByAgencyid(agencyId);
        return fuelTypes.stream().map(fuelTypeMapper::toDTO).toList();
    }

    /**
     * Get a fuel type by its name
     * 
     * @param fuelTypeName the name of the fuel type
     * @return the fuel type DTO if found
     */
    public Optional<FuelTypeDTO> getFuelTypeByName(String fuelTypeName) {
        Optional<FuelType> fuelType = fuelTypeRepository.findByFuelTypeName(fuelTypeName);
        return fuelType.map(fuelTypeMapper::toDTO);
    }

    /**
     * Update an existing fuel type
     * 
     * @param fuelTypeId  the ID of the fuel type to update
     * @param fuelTypeDTO the updated fuel type data
     * @return the updated fuel type DTO
     */
    public Optional<FuelTypeDTO> updateFuelType(UUID fuelTypeId, FuelTypeDTO fuelTypeDTO) {
        Optional<FuelType> existingFuelType = fuelTypeRepository.findById(fuelTypeId);
        if (existingFuelType.isPresent()) {
            FuelType fuelType = existingFuelType.get();
            fuelType.setFuelTypeName(fuelTypeDTO.getFuelTypeName());
            fuelType.setAgencyid(fuelTypeDTO.getAgencyId());
            FuelType updatedFuelType = fuelTypeRepository.save(fuelType);
            return Optional.of(fuelTypeMapper.toDTO(updatedFuelType));
        }
        return Optional.empty();
    }

    /**
     * Update an existing fuel type for a specific agency
     * 
     * @param agencyId    the ID of the agency
     * @param fuelTypeId  the ID of the fuel type to update
     * @param fuelTypeDTO the updated fuel type data
     * @return the updated fuel type DTO if successful
     */
    public Optional<FuelTypeDTO> updateFuelTypeScoped(String agencyId, UUID fuelTypeId, FuelTypeDTO fuelTypeDTO) {
        Optional<FuelType> existingFuelType = fuelTypeRepository.findById(fuelTypeId);
        if (existingFuelType.isPresent() && existingFuelType.get().getAgencyid().equals(agencyId)) {
            fuelTypeDTO.setAgencyId(agencyId); // Ensure agencyId remains correct
            return updateFuelType(fuelTypeId, fuelTypeDTO);
        }
        return Optional.empty();
    }

    /**
     * Delete a fuel type by its ID
     * 
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

    /**
     * Delete a fuel type for a specific agency
     * 
     * @param agencyId   the ID of the agency
     * @param fuelTypeId the ID of the fuel type to delete
     * @return true if deletion was successful
     */
    public boolean deleteFuelTypeScoped(String agencyId, UUID fuelTypeId) {
        Optional<FuelType> existingFuelType = fuelTypeRepository.findById(fuelTypeId);
        if (existingFuelType.isPresent() && existingFuelType.get().getAgencyid().equals(agencyId)) {
            return deleteFuelType(fuelTypeId);
        }
        return false;
    }
}
