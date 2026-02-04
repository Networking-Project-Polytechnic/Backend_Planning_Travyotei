package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.example.agencyadmin.DTOs.BusDTO;
import com.example.agencyadmin.Models.Bus;
import com.example.agencyadmin.Repositories.BusRepository;
import com.example.agencyadmin.Mappers.BusMapper;
import com.example.agencyadmin.Mappers.VehicleAmenityMapper;
import com.example.agencyadmin.Mappers.BusCanTransportMapper;
import com.example.agencyadmin.Mappers.BusReviewMapper;

/**
 * Service class for Bus entity.
 * This service encapsulates business logic for Bus operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to buses should be implemented in this service.
 */
@Service
public class BusService {

    /** The Bus repository for database operations */
    @Autowired
    private BusRepository busRepository;

    /** The Bus mapper for converting between entities and DTOs */
    @Autowired
    private BusMapper busMapper;

    @Autowired
    private VehicleAmenityMapper amenityMapper;

    @Autowired
    private BusCanTransportMapper transportMapper;

    @Autowired
    private BusReviewMapper reviewMapper;

    @Autowired
    private com.example.agencyadmin.Repositories.BusImageRepository busImageRepository;

    @Autowired
    private com.example.agencyadmin.Repositories.BusReviewRepository busReviewRepository;

    @Autowired
    private com.example.agencyadmin.Repositories.RoutePriceRepository routePriceRepository;

    @Autowired
    private com.example.agencyadmin.Repositories.ScheduleRepository scheduleRepository;

    @Autowired
    private com.example.agencyadmin.Repositories.AssignmentsRepository assignmentsRepository;

    /**
     * Create a new bus
     * 
     * @param busDTO the bus data transfer object
     * @return the created bus DTO
     */
    public BusDTO createBus(BusDTO busDTO) {
        Bus bus = busMapper.toEntity(busDTO);
        Bus savedBus = busRepository.save(bus);
        return busMapper.toDTO(savedBus);
    }

    /**
     * Create a new bus for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param busDTO   the bus data
     * @return the created bus DTO
     */
    public BusDTO createBusScoped(UUID agencyId, BusDTO busDTO) {
        busDTO.setAgencyId(agencyId);
        return createBus(busDTO);
    }

    /**
     * Get a bus by its ID
     * 
     * @param busId the ID of the bus
     * @return the bus DTO if found
     */
    public Optional<BusDTO> getBusById(UUID busId) {
        Optional<Bus> bus = busRepository.findById(busId);
        return bus.map(busMapper::toDTO);
    }

    /**
     * Get all buses
     * 
     * @return list of all bus DTOs
     */
    public List<BusDTO> getAllBuses() {
        List<Bus> buses = busRepository.findAll();
        return buses.stream().map(busMapper::toDTO).toList();
    }

    /**
     * Get all buses for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return list of bus DTOs for the agency
     */
    public List<BusDTO> getBusesByAgency(UUID agencyId) {
        List<Bus> buses = busRepository.findByAgencyId(agencyId);
        return buses.stream().map(busMapper::toDTO).toList();
    }

    /**
     * Get a bus by its registration number
     * 
     * @param registrationNumber the registration number
     * @return the bus DTO if found
     */
    public Optional<BusDTO> getBusByRegistrationNumber(String registrationNumber) {
        Bus bus = busRepository.findByRegistrationNumber(registrationNumber);
        return Optional.ofNullable(busMapper.toDTO(bus));
    }

    /**
     * Update an existing bus
     * 
     * @param busId  the ID of the bus to update
     * @param busDTO the updated bus data
     * @return the updated bus DTO
     */
    public Optional<BusDTO> updateBus(UUID busId, BusDTO busDTO) {
        Optional<Bus> existingBus = busRepository.findById(busId);
        if (existingBus.isPresent()) {
            Bus bus = existingBus.get();
            bus.setAgencyId(busDTO.getAgencyId());
            bus.setBusMakeId(busDTO.getBusMakeId());
            bus.setBusModelId(busDTO.getBusModelId());
            bus.setManufacturerId(busDTO.getManufacturerId());
            bus.setFuelTypeId(busDTO.getFuelTypeId());
            bus.setTransmissionTypeId(busDTO.getTransmissionTypeId());
            bus.setBusTypeId(busDTO.getBusTypeId());
            bus.setRegistrationNumber(busDTO.getRegistrationNumber());
            bus.setRegistrationExpiryDate(busDTO.getRegistrationExpiryDate());
            bus.setTotalSeats(busDTO.getTotalSeats());
            bus.setLuggageCapacityKg(busDTO.getLuggageCapacityKg());
            bus.setTankCapacityLiters(busDTO.getTankCapacityLiters());
            bus.setMileageKm(busDTO.getMileageKm());

            if (busDTO.getAmenities() != null) {
                bus.setAmenities(
                        busDTO.getAmenities().stream().map(amenityMapper::toEntity).collect(Collectors.toSet()));
            }
            if (busDTO.getCanTransport() != null) {
                bus.setCanTransport(
                        busDTO.getCanTransport().stream().map(transportMapper::toEntity).collect(Collectors.toSet()));
            }

            Bus updatedBus = busRepository.save(bus);
            return Optional.of(busMapper.toDTO(updatedBus));
        }
        return Optional.empty();
    }

    /**
     * Update an existing bus for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param busId    the ID of the bus to update
     * @param busDTO   the updated bus data
     * @return the updated bus DTO if successful
     */
    public Optional<BusDTO> updateBusScoped(UUID agencyId, UUID busId, BusDTO busDTO) {
        Optional<Bus> existingBus = busRepository.findById(busId);
        if (existingBus.isPresent() && existingBus.get().getAgencyId().equals(agencyId)) {
            busDTO.setAgencyId(agencyId); // Ensure agencyId remains correct
            return updateBus(busId, busDTO);
        }
        return Optional.empty();
    }

    /**
     * Delete a bus by its ID
     * 
     * @param busId the ID of the bus to delete
     * @return true if deletion was successful
     */
    @org.springframework.transaction.annotation.Transactional
    public boolean deleteBus(UUID busId) {
        if (busRepository.existsById(busId)) {
            // 1. Delete Bus Images
            busImageRepository.deleteByBusId(busId);

            // 2. Delete Bus Reviews
            busReviewRepository.deleteByBusBusId(busId);

            // 3. Find all schedules for this bus to delete their assignments first
            List<com.example.agencyadmin.Models.Schedule> schedules = scheduleRepository.findByBusid(busId);
            for (com.example.agencyadmin.Models.Schedule schedule : schedules) {
                assignmentsRepository.deleteByScheduleId(schedule.getScheduleid());
            }

            // 4. Delete Schedules
            scheduleRepository.deleteByBusid(busId);

            // 5. Delete Route Prices associated with this bus
            routePriceRepository.deleteByBusId(busId);

            // 6. Finally delete the bus
            busRepository.deleteById(busId);
            return true;
        }
        return false;
    }

    /**
     * Delete a bus for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param busId    the ID of the bus to delete
     * @return true if deletion was successful
     */
    @org.springframework.transaction.annotation.Transactional
    public boolean deleteBusScoped(UUID agencyId, UUID busId) {
        Optional<Bus> existingBus = busRepository.findById(busId);
        if (existingBus.isPresent() && existingBus.get().getAgencyId().equals(agencyId)) {
            return deleteBus(busId);
        }
        return false;
    }
}
