package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.agencyadmin.Models.Route;

/**
 * Repository interface for Route entity.
 * Provides database operations for Route entities including CRUD operations
 * and custom query methods to interact with the 'route' table.
 * 
 * This repository is used by the Route service to perform all database-related operations.
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, UUID> {
    
    /**
     * Find all routes for a specific agency
     * @param agencyid the ID of the agency
     * @return list of routes for the agency
     */
    java.util.List<Route> findByAgencyid(String agencyid);
    
    /**
     * Find routes between two locations
     * @param startlocationid the starting location ID
     * @param endlocationid the ending location ID
     * @return Route object if found, otherwise null
     */
    Route findByStartlocationidAndEndlocationid(UUID startlocationid, UUID endlocationid);
}
