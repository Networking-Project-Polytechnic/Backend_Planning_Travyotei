package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.agencyadmin.Models.RoutePrice;

/**
 * Repository interface for RoutePrice entity.
 * Provides database operations for RoutePrice entities including CRUD
 * operations
 * and custom query methods to interact with the 'route_price' table.
 * 
 * This repository is used by the RoutePrice service to perform all
 * database-related operations.
 */
@Repository
public interface RoutePriceRepository extends JpaRepository<RoutePrice, UUID> {

    /**
     * Find all prices for a specific route
     * 
     * @param routeId the ID of the route
     * @return list of prices for the route
     */
    java.util.List<RoutePrice> findByRouteId(UUID routeId);

    /**
     * Find all prices for a specific bus
     * 
     * @param busId the ID of the bus
     * @return list of prices for the bus
     */
    java.util.List<RoutePrice> findByBusId(UUID busId);

    java.util.List<RoutePrice> findByAgencyid(String agencyid);

    java.util.List<RoutePrice> findByRouteIdIn(java.util.List<UUID> routeIds);

    void deleteByBusId(UUID busId);
}
