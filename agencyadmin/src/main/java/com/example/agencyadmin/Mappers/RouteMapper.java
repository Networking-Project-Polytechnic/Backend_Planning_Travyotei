package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.RouteDTO;
import com.example.agencyadmin.Models.Route;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Route entity and RouteDTO.
 * This mapper is responsible for converting Route JPA entities to DTOs and vice
 * versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class RouteMapper {

    /**
     * Converts a Route entity to RouteDTO
     * 
     * @param route the Route entity
     * @return RouteDTO containing the data from the Route entity
     */
    public RouteDTO toDTO(Route route) {
        if (route == null) {
            return null;
        }
        return new RouteDTO(
                route.getRouteid(),
                route.getStartlocationid(),
                route.getEndlocationid(),
                route.getAgencyid(),
                route.getStopPoints());
    }

    /**
     * Converts a RouteDTO to Route entity
     * 
     * @param routeDTO the RouteDTO
     * @return Route entity containing the data from the DTO
     */
    public Route toEntity(RouteDTO routeDTO) {
        if (routeDTO == null) {
            return null;
        }
        Route route = new Route();
        route.setStartlocationid(routeDTO.getStartlocationid());
        route.setEndlocationid(routeDTO.getEndlocationid());
        route.setAgencyid(routeDTO.getAgencyid());
        route.setStopPoints(routeDTO.getStopPoints());
        return route;
    }
}
