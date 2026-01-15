package com.example.agencyadmin.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.agencyadmin.Models.Location;

/**
 * Repository interface for Location entity.
 * Provides database operations for Location entities including CRUD operations
 * and custom query methods to interact with the 'location' table.
 * 
 * This repository is used by the Location service to perform all database-related operations.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    
    /**
     * Find a location by its name (unique identifier)
     * @param locationname the name of the location
     * @return Location object if found, otherwise null
     */
    Location findByLocationname(String locationname);
    
    /**
     * Find all locations belonging to a specific agency
     * @param agencyid the ID of the agency
     * @return list of locations for the agency
     */
    java.util.List<Location> findByAgencyid(String agencyid);
}
