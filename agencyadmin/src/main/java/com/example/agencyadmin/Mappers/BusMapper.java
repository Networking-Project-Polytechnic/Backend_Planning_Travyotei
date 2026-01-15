package com.example.agencyadmin.Mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.agencyadmin.DTOs.BusDTO;
import com.example.agencyadmin.Models.Bus;

/**
 * Mapper class for converting between Bus entity and BusDTO.
 * This mapper is responsible for converting Bus JPA entities to DTOs and vice
 * versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class BusMapper {

    private final BusReviewMapper reviewMapper;
    private final VehicleAmenityMapper amenityMapper;
    private final BusCanTransportMapper transportMapper;

    public BusMapper(BusReviewMapper reviewMapper, VehicleAmenityMapper amenityMapper,
            BusCanTransportMapper transportMapper) {
        this.reviewMapper = reviewMapper;
        this.amenityMapper = amenityMapper;
        this.transportMapper = transportMapper;
    }

    /**
     * Converts a Bus entity to BusDTO
     * 
     * @param bus the Bus entity
     * @return BusDTO containing the data from the Bus entity
     */
    public BusDTO toDTO(Bus bus) {
        if (bus == null) {
            return null;
        }
        BusDTO dto = new BusDTO();
        dto.setBusId(bus.getBusId());
        dto.setAgencyId(bus.getAgencyId());
        dto.setBusMakeId(bus.getBusMakeId());
        dto.setBusModelId(bus.getBusModelId());
        dto.setManufacturerId(bus.getManufacturerId());
        dto.setFuelTypeId(bus.getFuelTypeId());
        dto.setTransmissionTypeId(bus.getTransmissionTypeId());
        dto.setBusTypeId(bus.getBusTypeId());
        dto.setRegistrationNumber(bus.getRegistrationNumber());
        dto.setRegistrationExpiryDate(bus.getRegistrationExpiryDate());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setLuggageCapacityKg(bus.getLuggageCapacityKg());
        dto.setTankCapacityLiters(bus.getTankCapacityLiters());
        dto.setMileageKm(bus.getMileageKm());

        if (bus.getReviews() != null) {
            dto.setReviews(bus.getReviews().stream().map(reviewMapper::toDTO).collect(Collectors.toSet()));
        }
        if (bus.getAmenities() != null) {
            dto.setAmenities(bus.getAmenities().stream().map(amenityMapper::toDTO).collect(Collectors.toSet()));
        }
        if (bus.getCanTransport() != null) {
            dto.setCanTransport(bus.getCanTransport().stream().map(transportMapper::toDTO).collect(Collectors.toSet()));
        }

        return dto;
    }

    /**
     * Converts a BusDTO to Bus entity
     * 
     * @param busDTO the BusDTO
     * @return Bus entity containing the data from the DTO
     */
    public Bus toEntity(BusDTO busDTO) {
        if (busDTO == null) {
            return null;
        }
        Bus bus = new Bus();
        bus.setBusId(busDTO.getBusId());
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

        if (busDTO.getReviews() != null) {
            bus.setReviews(busDTO.getReviews().stream().map(reviewMapper::toEntity).collect(Collectors.toSet()));
        }
        if (busDTO.getAmenities() != null) {
            bus.setAmenities(busDTO.getAmenities().stream().map(amenityMapper::toEntity).collect(Collectors.toSet()));
        }
        if (busDTO.getCanTransport() != null) {
            bus.setCanTransport(
                    busDTO.getCanTransport().stream().map(transportMapper::toEntity).collect(Collectors.toSet()));
        }

        return bus;
    }
}
