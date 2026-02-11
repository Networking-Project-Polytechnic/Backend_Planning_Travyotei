package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.agencyadmin.DTOs.RoutePriceDTO;
import com.example.agencyadmin.Models.RoutePrice;
import com.example.agencyadmin.Repositories.RoutePriceRepository;
import com.example.agencyadmin.Mappers.RoutePriceMapper;

/**
 * Service class for RoutePrice entity.
 * This service encapsulates business logic for RoutePrice operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to route prices should be implemented in this
 * service.
 */
@Service
@Transactional(readOnly = true)
public class RoutePriceService {

    /** The RoutePrice repository for database operations */
    @Autowired
    private RoutePriceRepository routePriceRepository;

    /** The RoutePrice mapper for converting between entities and DTOs */
    @Autowired
    private RoutePriceMapper routePriceMapper;

    @Transactional
    public RoutePriceDTO createRoutePrice(RoutePriceDTO routePriceDTO) {
        RoutePrice routePrice = routePriceMapper.toEntity(routePriceDTO);
        RoutePrice savedRoutePrice = routePriceRepository.save(routePrice);
        return routePriceMapper.toDTO(savedRoutePrice);
    }

    /**
     * Create a new route price for a specific agency
     * 
     * @param agencyId      the ID of the agency
     * @param routePriceDTO the route price data
     * @return the created route price DTO
     */
    public RoutePriceDTO createRoutePriceScoped(String agencyId, RoutePriceDTO routePriceDTO) {
        routePriceDTO.setAgencyid(agencyId);
        return createRoutePrice(routePriceDTO);
    }

    /**
     * Get a route price by its ID
     * 
     * @param priceId the ID of the route price
     * @return the route price DTO if found
     */
    public Optional<RoutePriceDTO> getRoutePriceById(UUID priceId) {
        Optional<RoutePrice> routePrice = routePriceRepository.findById(priceId);
        return routePrice.map(routePriceMapper::toDTO);
    }

    /**
     * Get all route prices
     * 
     * @return list of all route price DTOs
     */
    public List<RoutePriceDTO> getAllRoutePrices() {
        List<RoutePrice> routePrices = routePriceRepository.findAll();
        return routePrices.stream().map(routePriceMapper::toDTO).toList();
    }

    /**
     * Get all prices for a specific route
     * 
     * @param routeId the ID of the route
     * @return list of route price DTOs for the route
     */
    public List<RoutePriceDTO> getPricesByRoute(UUID routeId) {
        List<RoutePrice> routePrices = routePriceRepository.findByRouteId(routeId);
        return routePrices.stream().map(routePriceMapper::toDTO).toList();
    }

    public List<RoutePriceDTO> getPricesByAgency(String agencyId) {
        List<RoutePrice> routePrices = routePriceRepository.findByAgencyid(agencyId);
        return routePrices.stream().map(routePriceMapper::toDTO).toList();
    }

    public List<RoutePriceDTO> getPricesByRouteIds(List<UUID> routeIds) {
        return routePriceRepository.findByRouteIdIn(routeIds).stream()
                .map(routePriceMapper::toDTO)
                .toList();
    }

    /**
     * Get all prices for a specific bus
     * 
     * @param busId the ID of the bus
     * @return list of route price DTOs for the bus
     */
    public List<RoutePriceDTO> getPricesByBus(UUID busId) {
        List<RoutePrice> routePrices = routePriceRepository.findByBusId(busId);
        return routePrices.stream().map(routePriceMapper::toDTO).toList();
    }

    /**
     * Update an existing route price
     * 
     * @param priceId       the ID of the route price to update
     * @param routePriceDTO the updated route price data
     * @return the updated route price DTO
     */
    @Transactional
    public Optional<RoutePriceDTO> updateRoutePrice(UUID priceId, RoutePriceDTO routePriceDTO) {
        Optional<RoutePrice> existingRoutePrice = routePriceRepository.findById(priceId);
        if (existingRoutePrice.isPresent()) {
            RoutePrice routePrice = existingRoutePrice.get();
            routePrice.setRouteId(routePriceDTO.getRouteId());
            routePrice.setBusId(routePriceDTO.getBusId());
            routePrice.setAgencyid(routePriceDTO.getAgencyid());
            routePrice.setPriceAmount(routePriceDTO.getPriceAmount());
            routePrice.setCurrency(routePriceDTO.getCurrency());
            RoutePrice updatedRoutePrice = routePriceRepository.save(routePrice);
            return Optional.of(routePriceMapper.toDTO(updatedRoutePrice));
        }
        return Optional.empty();
    }

    /**
     * Update an existing route price for a specific agency
     * 
     * @param agencyId      the ID of the agency
     * @param priceId       the ID of the route price to update
     * @param routePriceDTO the updated route price data
     * @return the updated route price DTO if successful
     */
    public Optional<RoutePriceDTO> updateRoutePriceScoped(String agencyId, UUID priceId, RoutePriceDTO routePriceDTO) {
        Optional<RoutePrice> existingRoutePrice = routePriceRepository.findById(priceId);
        if (existingRoutePrice.isPresent() && existingRoutePrice.get().getAgencyid().equals(agencyId)) {
            routePriceDTO.setAgencyid(agencyId); // Ensure agencyid remains correct
            return updateRoutePrice(priceId, routePriceDTO);
        }
        return Optional.empty();
    }

    /**
     * Delete a route price by its ID
     * 
     * @param priceId the ID of the route price to delete
     * @return true if deletion was successful
     */
    @Transactional
    public boolean deleteRoutePrice(UUID priceId) {
        if (routePriceRepository.existsById(priceId)) {
            routePriceRepository.deleteById(priceId);
            return true;
        }
        return false;
    }

    /**
     * Delete a route price for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param priceId  the ID of the route price to delete
     * @return true if deletion was successful
     */
    public boolean deleteRoutePriceScoped(String agencyId, UUID priceId) {
        Optional<RoutePrice> existingRoutePrice = routePriceRepository.findById(priceId);
        if (existingRoutePrice.isPresent() && existingRoutePrice.get().getAgencyid().equals(agencyId)) {
            return deleteRoutePrice(priceId);
        }
        return false;
    }
}
