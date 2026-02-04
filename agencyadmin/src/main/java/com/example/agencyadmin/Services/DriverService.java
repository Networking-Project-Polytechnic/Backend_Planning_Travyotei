package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.DriverDTO;
import com.example.agencyadmin.Models.Driver;
import com.example.agencyadmin.Repositories.DriverRepository;
import com.example.agencyadmin.Mappers.DriverMapper;

/**
 * Service class for Driver entity.
 * This service encapsulates business logic for Driver operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to drivers should be implemented in this service.
 */
@Service
public class DriverService {

    /** The Driver repository for database operations */
    @Autowired
    private DriverRepository driverRepository;

    /** The Driver mapper for converting between entities and DTOs */
    @Autowired
    private DriverMapper driverMapper;

    public DriverDTO createDriver(DriverDTO driverDTO) {
        Driver driver = driverMapper.toEntity(driverDTO);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toDTO(savedDriver);
    }

    /**
     * Create a new driver for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param driverDTO the driver data
     * @return the created driver DTO
     */
    public DriverDTO createDriverScoped(String agencyId, DriverDTO driverDTO) {
        driverDTO.setAgencyid(agencyId);
        return createDriver(driverDTO);
    }

    /**
     * Get a driver by its ID
     * 
     * @param driverId the ID of the driver
     * @return the driver DTO if found
     */
    public Optional<DriverDTO> getDriverById(UUID driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        return driver.map(driverMapper::toDTO);
    }

    /**
     * Get all drivers
     * 
     * @return list of all driver DTOs
     */
    public List<DriverDTO> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(driverMapper::toDTO).toList();
    }

    /**
     * Get all drivers for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return list of driver DTOs for the agency
     */
    public List<DriverDTO> getDriversByAgency(String agencyId) {
        List<Driver> drivers = driverRepository.findByAgencyid(agencyId);
        return drivers.stream().map(driverMapper::toDTO).toList();
    }

    /**
     * Get a driver by their license number
     * 
     * @param licenseNumber the license number
     * @return the driver DTO if found
     */
    public Optional<DriverDTO> getDriverByLicenseNumber(String licenseNumber) {
        Driver driver = driverRepository.findByLicenseNumber(licenseNumber);
        if (driver == null) {
            return Optional.empty();
        }
        return Optional.of(driverMapper.toDTO(driver));
    }

    /**
     * Get a driver by their full name
     * 
     * @param fullName the full name of the driver
     * @return the driver DTO if found
     */
    public Optional<DriverDTO> getDriverByFullName(String fullName) {
        Driver driver = driverRepository.findByFullName(fullName);
        if (driver == null) {
            return Optional.empty();
        }
        return Optional.of(driverMapper.toDTO(driver));
    }

    /**
     * Update an existing driver
     * 
     * @param driverId  the ID of the driver to update
     * @param driverDTO the updated driver data
     * @return the updated driver DTO
     */
    public Optional<DriverDTO> updateDriver(UUID driverId, DriverDTO driverDTO) {
        Optional<Driver> existingDriver = driverRepository.findById(driverId);
        if (existingDriver.isPresent()) {
            Driver driver = existingDriver.get();
            driver.setFullName(driverDTO.getFullName());
            driver.setPhone(driverDTO.getPhone());
            driver.setLicenseNumber(driverDTO.getLicenseNumber());
            driver.setAgencyid(driverDTO.getAgencyid());
            driver.setDescription(driverDTO.getDescription());
            Driver updatedDriver = driverRepository.save(driver);
            return Optional.of(driverMapper.toDTO(updatedDriver));
        }
        return Optional.empty();
    }

    /**
     * Update an existing driver for a specific agency
     * 
     * @param agencyId  the ID of the agency
     * @param driverId  the ID of the driver to update
     * @param driverDTO the updated driver data
     * @return the updated driver DTO if successful
     */
    public Optional<DriverDTO> updateDriverScoped(String agencyId, UUID driverId, DriverDTO driverDTO) {
        Optional<Driver> existingDriver = driverRepository.findById(driverId);
        if (existingDriver.isPresent() && existingDriver.get().getAgencyid().equals(agencyId)) {
            driverDTO.setAgencyid(agencyId); // Ensure agencyid remains correct
            return updateDriver(driverId, driverDTO);
        }
        return Optional.empty();
    }

    /**
     * Delete a driver by its ID
     * 
     * @param driverId the ID of the driver to delete
     * @return true if deletion was successful
     */
    public boolean deleteDriver(UUID driverId) {
        if (driverRepository.existsById(driverId)) {
            driverRepository.deleteById(driverId);
            return true;
        }
        return false;
    }

    /**
     * Delete a driver for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param driverId the ID of the driver to delete
     * @return true if deletion was successful
     */
    public boolean deleteDriverScoped(String agencyId, UUID driverId) {
        Optional<Driver> existingDriver = driverRepository.findById(driverId);
        if (existingDriver.isPresent() && existingDriver.get().getAgencyid().equals(agencyId)) {
            return deleteDriver(driverId);
        }
        return false;
    }
}
