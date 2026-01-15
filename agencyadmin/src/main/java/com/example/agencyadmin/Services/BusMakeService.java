package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.BusMakeDTO;
import com.example.agencyadmin.Models.BusMakes;
import com.example.agencyadmin.Repositories.BusMakeRepository;
import com.example.agencyadmin.Mappers.BusMakeMapper;

/**
 * Service class for BusMakes entity.
 * This service encapsulates business logic for BusMakes operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to bus makes should be implemented in this service.
 */
@Service
public class BusMakeService {
    
    /** The BusMakes repository for database operations */
    @Autowired
    private BusMakeRepository busMakeRepository;
    
    /** The BusMake mapper for converting between entities and DTOs */
    @Autowired
    private BusMakeMapper busMakeMapper;
    
    /**
     * Create a new bus make
     * @param busMakeDTO the bus make data transfer object
     * @return the created bus make DTO
     */
    public BusMakeDTO createBusMake(BusMakeDTO busMakeDTO) {
        BusMakes busMake = busMakeMapper.toEntity(busMakeDTO);
        BusMakes savedBusMake = busMakeRepository.save(busMake);
        return busMakeMapper.toDTO(savedBusMake);
    }
    
    /**
     * Get a bus make by its ID
     * @param busMakeId the ID of the bus make
     * @return the bus make DTO if found
     */
    public Optional<BusMakeDTO> getBusMakeById(UUID busMakeId) {
        Optional<BusMakes> busMake = busMakeRepository.findById(busMakeId);
        return busMake.map(busMakeMapper::toDTO);
    }
    
    /**
     * Get all bus makes
     * @return list of all bus make DTOs
     */
    public List<BusMakeDTO> getAllBusMakes() {
        List<BusMakes> busMakes = busMakeRepository.findAll();
        return busMakes.stream().map(busMakeMapper::toDTO).toList();
    }
    
    /**
     * Get a bus make by its name
     * @param makeName the name of the bus make
     * @return the bus make DTO if found
     */
    public Optional<BusMakeDTO> getBusMakeByName(String makeName) {
        Optional<BusMakes> busMake = busMakeRepository.findByMakeName(makeName);
        return busMake.map(busMakeMapper::toDTO);
    }
    
    /**
     * Update an existing bus make
     * @param busMakeId the ID of the bus make to update
     * @param busMakeDTO the updated bus make data
     * @return the updated bus make DTO
     */
    public Optional<BusMakeDTO> updateBusMake(UUID busMakeId, BusMakeDTO busMakeDTO) {
        Optional<BusMakes> existingBusMake = busMakeRepository.findById(busMakeId);
        if (existingBusMake.isPresent()) {
            BusMakes busMake = existingBusMake.get();
            busMake.setMakeName(busMakeDTO.getMakeName());
            BusMakes updatedBusMake = busMakeRepository.save(busMake);
            return Optional.of(busMakeMapper.toDTO(updatedBusMake));
        }
        return Optional.empty();
    }
    
    /**
     * Delete a bus make by its ID
     * @param busMakeId the ID of the bus make to delete
     * @return true if deletion was successful
     */
    public boolean deleteBusMake(UUID busMakeId) {
        if (busMakeRepository.existsById(busMakeId)) {
            busMakeRepository.deleteById(busMakeId);
            return true;
        }
        return false;
    }
}

