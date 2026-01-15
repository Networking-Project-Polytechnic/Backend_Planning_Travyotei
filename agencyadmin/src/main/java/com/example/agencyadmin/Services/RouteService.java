package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.agencyadmin.DTOs.RouteDTO;
import com.example.agencyadmin.Models.Route;
import com.example.agencyadmin.Repositories.RouteRepository;
import com.example.agencyadmin.Mappers.RouteMapper;

/**
 * Service class for Route entity.
 * This service encapsulates business logic for Route operations.
 * It handles interactions between the controller and repository layers.
 * All business logic related to routes should be implemented in this service.
 */
@Service
public class RouteService {

    /** The Route repository for database operations */
    @Autowired
    private RouteRepository routeRepository;

    /** The Route mapper for converting between entities and DTOs */
    @Autowired
    private RouteMapper routeMapper;

    /**
     * Create a new route
     * 
     * @param routeDTO the route data transfer object
     * @return the created route DTO
     */
    public RouteDTO createRoute(RouteDTO routeDTO) {
        Route route = routeMapper.toEntity(routeDTO);
        Route savedRoute = routeRepository.save(route);
        return routeMapper.toDTO(savedRoute);
    }

    /**
     * Get a route by its ID
     * 
     * @param routeId the ID of the route
     * @return the route DTO if found
     */
    public Optional<RouteDTO> getRouteById(UUID routeId) {
        Optional<Route> route = routeRepository.findById(routeId);
        return route.map(routeMapper::toDTO);
    }

    /**
     * Get all routes
     * 
     * @return list of all route DTOs
     */
    public List<RouteDTO> getAllRoutes() {
        List<Route> routes = routeRepository.findAll();
        return routes.stream().map(routeMapper::toDTO).toList();
    }

    /**
     * Get all routes for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return list of route DTOs for the agency
     */
    public List<RouteDTO> getRoutesByAgency(String agencyId) {
        List<Route> routes = routeRepository.findByAgencyid(agencyId);
        return routes.stream().map(routeMapper::toDTO).toList();
    }

    /**
     * Get a route between two locations
     * 
     * @param startLocationId the starting location ID
     * @param endLocationId   the ending location ID
     * @return the route DTO if found
     */
    public Optional<RouteDTO> getRouteBetweenLocations(UUID startLocationId, UUID endLocationId) {
        Route route = routeRepository.findByStartlocationidAndEndlocationid(startLocationId, endLocationId);
        return Optional.ofNullable(routeMapper.toDTO(route));
    }

    /**
     * Update an existing route
     * 
     * @param routeId  the ID of the route to update
     * @param routeDTO the updated route data
     * @return the updated route DTO
     */
    public Optional<RouteDTO> updateRoute(UUID routeId, RouteDTO routeDTO) {
        Optional<Route> existingRoute = routeRepository.findById(routeId);
        if (existingRoute.isPresent()) {
            Route route = existingRoute.get();
            route.setStartlocationid(routeDTO.getStartlocationid());
            route.setEndlocationid(routeDTO.getEndlocationid());
            route.setAgencyid(routeDTO.getAgencyid());
            route.setStopPoints(routeDTO.getStopPoints());
            Route updatedRoute = routeRepository.save(route);
            return Optional.of(routeMapper.toDTO(updatedRoute));
        }
        return Optional.empty();
    }

    /**
     * Delete a route by its ID
     * 
     * @param routeId the ID of the route to delete
     * @return true if deletion was successful
     */
    public boolean deleteRoute(UUID routeId) {
        if (routeRepository.existsById(routeId)) {
            routeRepository.deleteById(routeId);
            return true;
        }
        return false;
    }
}
