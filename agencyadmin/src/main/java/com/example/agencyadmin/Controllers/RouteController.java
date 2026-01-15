package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.RouteDTO;
import com.example.agencyadmin.Services.RouteService;

/**
 * REST Controller for Route entity.
 * Handles HTTP requests related to route operations.
 * Provides endpoints for CRUD operations and route queries.
 * 
 * Base path: /api/v1/routes
 */
@RestController
@RequestMapping("/api/v1/routes")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RouteController {
    
    /** The Route service for business logic */
    @Autowired
    private RouteService routeService;
    
    /**
     * Create a new route
     * @param routeDTO the route data transfer object
     * @return ResponseEntity with the created route DTO and HTTP 201 Created status
     */
    @PostMapping
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO createdRoute = routeService.createRoute(routeDTO);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }
    
    /**
     * Get a route by its ID
     * @param routeId the ID of the route
     * @return ResponseEntity with the route DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/{routeId}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable UUID routeId) {
        return routeService.getRouteById(routeId)
                .map(route -> new ResponseEntity<>(route, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Get all routes
     * @return ResponseEntity with list of all route DTOs and HTTP 200 OK status
     */
    @GetMapping
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {
        List<RouteDTO> routes = routeService.getAllRoutes();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }
    
    /**
     * Get all routes for a specific agency
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of route DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<RouteDTO>> getRoutesByAgency(@PathVariable String agencyId) {
        List<RouteDTO> routes = routeService.getRoutesByAgency(agencyId);
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }
    
    /**
     * Get a route between two locations
     * @param startLocationId the starting location ID
     * @param endLocationId the ending location ID
     * @return ResponseEntity with the route DTO if found, otherwise 404 Not Found
     */
    @GetMapping("/locations/{startLocationId}/{endLocationId}")
    public ResponseEntity<RouteDTO> getRouteBetweenLocations(@PathVariable UUID startLocationId, @PathVariable UUID endLocationId) {
        return routeService.getRouteBetweenLocations(startLocationId, endLocationId)
                .map(route -> new ResponseEntity<>(route, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Update an existing route
     * @param routeId the ID of the route to update
     * @param routeDTO the updated route data
     * @return ResponseEntity with the updated route DTO if successful, otherwise 404 Not Found
     */
    @PutMapping("/{routeId}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable UUID routeId, @RequestBody RouteDTO routeDTO) {
        return routeService.updateRoute(routeId, routeDTO)
                .map(route -> new ResponseEntity<>(route, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * Delete a route by its ID
     * @param routeId the ID of the route to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404 Not Found
     */
    @DeleteMapping("/{routeId}")
    public ResponseEntity<Void> deleteRoute(@PathVariable UUID routeId) {
        if (routeService.deleteRoute(routeId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
