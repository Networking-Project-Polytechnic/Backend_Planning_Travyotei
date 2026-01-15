package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.BusModelDTO;
import com.example.agencyadmin.Models.BusModels;
import com.example.agencyadmin.Repositories.BusModelRepository;
import com.example.agencyadmin.Mappers.BusModelMapper;

/**
 * Service class for BusModels entity.
 * This service encapsulates business logic for BusModels operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to bus models should be implemented in this service.
 */
@Service
public class BusModelService {
    
    /** The BusModels repository for database operations */
    @Autowired
    private BusModelRepository busModelRepository;
    
    /** The BusModel mapper for converting between entities and DTOs */
    @Autowired
    private BusModelMapper busModelMapper;
    
    /**
     * Create a new bus model
     * @param busModelDTO the bus model data transfer object
     * @return the created bus model DTO
     */
    public BusModelDTO createBusModel(BusModelDTO busModelDTO) {
        BusModels busModel = busModelMapper.toEntity(busModelDTO);
        BusModels savedBusModel = busModelRepository.save(busModel);
        return busModelMapper.toDTO(savedBusModel);
    }
    
    /**
     * Get a bus model by its ID
     * @param busModelId the ID of the bus model
     * @return the bus model DTO if found
     */
    public Optional<BusModelDTO> getBusModelById(UUID busModelId) {
        Optional<BusModels> busModel = busModelRepository.findById(busModelId);
        return busModel.map(busModelMapper::toDTO);
    }
    
    /**
     * Get all bus models
     * @return list of all bus model DTOs
     */
    public List<BusModelDTO> getAllBusModels() {
        List<BusModels> busModels = busModelRepository.findAll();
        return busModels.stream().map(busModelMapper::toDTO).toList();
    }
    
    /**
     * Get a bus model by its name
     * @param modelName the name of the bus model
     * @return the bus model DTO if found
     */
    public Optional<BusModelDTO> getBusModelByName(String modelName) {
        Optional<BusModels> busModel = busModelRepository.findByModelName(modelName);
        return busModel.map(busModelMapper::toDTO);
    }
    
    /**
     * Update an existing bus model
     * @param busModelId the ID of the bus model to update
     * @param busModelDTO the updated bus model data
     * @return the updated bus model DTO
     */
    public Optional<BusModelDTO> updateBusModel(UUID busModelId, BusModelDTO busModelDTO) {
        Optional<BusModels> existingBusModel = busModelRepository.findById(busModelId);
        if (existingBusModel.isPresent()) {
            BusModels busModel = existingBusModel.get();
            busModel.setModelName(busModelDTO.getModelName());
            BusModels updatedBusModel = busModelRepository.save(busModel);
            return Optional.of(busModelMapper.toDTO(updatedBusModel));
        }
        return Optional.empty();
    }
    
    /**
     * Delete a bus model by its ID
     * @param busModelId the ID of the bus model to delete
     * @return true if deletion was successful
     */
    public boolean deleteBusModel(UUID busModelId) {
        if (busModelRepository.existsById(busModelId)) {
            busModelRepository.deleteById(busModelId);
            return true;
        }
        return false;
    }
}

