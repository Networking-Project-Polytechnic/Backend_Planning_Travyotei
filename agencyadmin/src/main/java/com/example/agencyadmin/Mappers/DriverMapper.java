package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.DriverDTO;
import com.example.agencyadmin.Models.Driver;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Driver entity and DriverDTO.
 * This mapper is responsible for converting Driver JPA entities to DTOs and
 * vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class DriverMapper {

    /**
     * Converts a Driver entity to DriverDTO
     * 
     * @param driver the Driver entity
     * @return DriverDTO containing the data from the Driver entity
     */
    public DriverDTO toDTO(Driver driver) {
        if (driver == null) {
            return null;
        }
        return new DriverDTO(
                driver.getDriverId(),
                driver.getFullName(),
                driver.getPhone(),
                driver.getLicenseNumber(),
                driver.getAgencyid(),
                driver.getDescription());
    }

    /**
     * Converts a DriverDTO to Driver entity
     * 
     * @param driverDTO the DriverDTO
     * @return Driver entity containing the data from the DTO
     */
    public Driver toEntity(DriverDTO driverDTO) {
        if (driverDTO == null) {
            return null;
        }
        Driver driver = new Driver();
        driver.setFullName(driverDTO.getFullName());
        driver.setPhone(driverDTO.getPhone());
        driver.setLicenseNumber(driverDTO.getLicenseNumber());
        driver.setAgencyid(driverDTO.getAgencyid());
        driver.setDescription(driverDTO.getDescription());
        return driver;
    }
}
