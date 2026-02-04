package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.BusManufacturerDTO;
import com.example.agencyadmin.Models.BusManufacturers;
import com.example.agencyadmin.Repositories.BusManufacturerRepository;
import com.example.agencyadmin.Mappers.BusManufacturerMapper;

/**
 * Service class for BusManufacturers entity.
 * This service encapsulates business logic for BusManufacturers operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to bus manufacturers should be implemented in this
 * service.
 */
@Service
public class BusManufacturerService {

    /** The BusManufacturers repository for database operations */
    @Autowired
    private BusManufacturerRepository busManufacturerRepository;

    /** The BusManufacturer mapper for converting between entities and DTOs */
    @Autowired
    private BusManufacturerMapper busManufacturerMapper;

    public BusManufacturerDTO createBusManufacturer(BusManufacturerDTO busManufacturerDTO) {
        BusManufacturers busManufacturer = busManufacturerMapper.toEntity(busManufacturerDTO);
        BusManufacturers savedBusManufacturer = busManufacturerRepository.save(busManufacturer);
        return busManufacturerMapper.toDTO(savedBusManufacturer);
    }

    /**
     * Create a new bus manufacturer for a specific agency
     * 
     * @param agencyId           the ID of the agency
     * @param busManufacturerDTO the bus manufacturer data
     * @return the created bus manufacturer DTO
     */
    public BusManufacturerDTO createBusManufacturerScoped(String agencyId, BusManufacturerDTO busManufacturerDTO) {
        busManufacturerDTO.setAgencyId(agencyId);
        return createBusManufacturer(busManufacturerDTO);
    }

    /**
     * Get a bus manufacturer by its ID
     * 
     * @param manufacturerId the ID of the bus manufacturer
     * @return the bus manufacturer DTO if found
     */
    public Optional<BusManufacturerDTO> getBusManufacturerById(UUID manufacturerId) {
        Optional<BusManufacturers> busManufacturer = busManufacturerRepository.findById(manufacturerId);
        return busManufacturer.map(busManufacturerMapper::toDTO);
    }

    /**
     * Get all bus manufacturers
     * 
     * @return list of all bus manufacturer DTOs
     */
    public List<BusManufacturerDTO> getAllBusManufacturers() {
        List<BusManufacturers> busManufacturers = busManufacturerRepository.findAll();
        return busManufacturers.stream().map(busManufacturerMapper::toDTO).toList();
    }

    public List<BusManufacturerDTO> getBusManufacturersByAgency(String agencyId) {
        List<BusManufacturers> busManufacturers = busManufacturerRepository.findByAgencyid(agencyId);
        return busManufacturers.stream().map(busManufacturerMapper::toDTO).toList();
    }

    /**
     * Get a bus manufacturer by its name
     * 
     * @param manufacturerName the name of the bus manufacturer
     * @return the bus manufacturer DTO if found
     */
    public Optional<BusManufacturerDTO> getBusManufacturerByName(String manufacturerName) {
        Optional<BusManufacturers> busManufacturer = busManufacturerRepository.findByManufacturerName(manufacturerName);
        return busManufacturer.map(busManufacturerMapper::toDTO);
    }

    /**
     * Update an existing bus manufacturer
     * 
     * @param manufacturerId     the ID of the bus manufacturer to update
     * @param busManufacturerDTO the updated bus manufacturer data
     * @return the updated bus manufacturer DTO
     */
    public Optional<BusManufacturerDTO> updateBusManufacturer(UUID manufacturerId,
            BusManufacturerDTO busManufacturerDTO) {
        Optional<BusManufacturers> existingBusManufacturer = busManufacturerRepository.findById(manufacturerId);
        if (existingBusManufacturer.isPresent()) {
            BusManufacturers busManufacturer = existingBusManufacturer.get();
            busManufacturer.setManufacturerName(busManufacturerDTO.getManufacturerName());
            busManufacturer.setAgencyid(busManufacturerDTO.getAgencyId());
            BusManufacturers updatedBusManufacturer = busManufacturerRepository.save(busManufacturer);
            return Optional.of(busManufacturerMapper.toDTO(updatedBusManufacturer));
        }
        return Optional.empty();
    }

    /**
     * Update an existing bus manufacturer for a specific agency
     * 
     * @param agencyId           the ID of the agency
     * @param manufacturerId     the ID of the bus manufacturer to update
     * @param busManufacturerDTO the updated bus manufacturer data
     * @return the updated bus manufacturer DTO if successful
     */
    public Optional<BusManufacturerDTO> updateBusManufacturerScoped(String agencyId, UUID manufacturerId,
            BusManufacturerDTO busManufacturerDTO) {
        Optional<BusManufacturers> existingBusManufacturer = busManufacturerRepository.findById(manufacturerId);
        if (existingBusManufacturer.isPresent() && existingBusManufacturer.get().getAgencyid().equals(agencyId)) {
            busManufacturerDTO.setAgencyId(agencyId); // Ensure agencyId remains correct
            return updateBusManufacturer(manufacturerId, busManufacturerDTO);
        }
        return Optional.empty();
    }

    /**
     * Delete a bus manufacturer by its ID
     * 
     * @param manufacturerId the ID of the bus manufacturer to delete
     * @return true if deletion was successful
     */
    public boolean deleteBusManufacturer(UUID manufacturerId) {
        if (busManufacturerRepository.existsById(manufacturerId)) {
            busManufacturerRepository.deleteById(manufacturerId);
            return true;
        }
        return false;
    }

    /**
     * Delete a bus manufacturer for a specific agency
     * 
     * @param agencyId       the ID of the agency
     * @param manufacturerId the ID of the bus manufacturer to delete
     * @return true if deletion was successful
     */
    public boolean deleteBusManufacturerScoped(String agencyId, UUID manufacturerId) {
        Optional<BusManufacturers> existingBusManufacturer = busManufacturerRepository.findById(manufacturerId);
        if (existingBusManufacturer.isPresent() && existingBusManufacturer.get().getAgencyid().equals(agencyId)) {
            return deleteBusManufacturer(manufacturerId);
        }
        return false;
    }
}
