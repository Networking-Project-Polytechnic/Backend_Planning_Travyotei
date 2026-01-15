package com.example.agencyadmin.Mappers;

import com.example.agencyadmin.DTOs.RoutePriceDTO;
import com.example.agencyadmin.Models.RoutePrice;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between RoutePrice entity and RoutePriceDTO.
 * This mapper is responsible for converting RoutePrice JPA entities to DTOs and
 * vice versa.
 * It is used to decouple the database layer from the API layer.
 */
@Component
public class RoutePriceMapper {

    public RoutePriceDTO toDTO(RoutePrice routePrice) {
        if (routePrice == null) {
            return null;
        }
        return new RoutePriceDTO(
                routePrice.getPriceId(),
                routePrice.getRouteId(),
                routePrice.getBusId(),
                routePrice.getPriceAmount(),
                routePrice.getCurrency());
    }

    /**
     * Converts a RoutePriceDTO to RoutePrice entity
     * 
     * @param routePriceDTO the RoutePriceDTO
     * @return RoutePrice entity containing the data from the DTO
     */
    public RoutePrice toEntity(RoutePriceDTO routePriceDTO) {
        if (routePriceDTO == null) {
            return null;
        }
        RoutePrice routePrice = new RoutePrice();
        routePrice.setRouteId(routePriceDTO.getRouteId());
        routePrice.setBusId(routePriceDTO.getBusId());
        routePrice.setPriceAmount(routePriceDTO.getPriceAmount());
        routePrice.setCurrency(routePriceDTO.getCurrency());
        return routePrice;
    }
}
