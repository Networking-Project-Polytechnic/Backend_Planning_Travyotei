package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.agencyadmin.DTOs.RoutePriceDTO;
import com.example.agencyadmin.Services.RoutePriceService;

/**
 * REST Controller for RoutePrice entity.
 * Handles HTTP requests related to route price operations.
 * Provides endpoints for CRUD operations and route price queries.
 * 
 * Base path: /api/v1/route-prices
 */
@RestController
@RequestMapping("/api/v1/route-prices")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoutePriceController {

    /** The RoutePrice service for business logic */
    @Autowired
    private RoutePriceService routePriceService;

    /**
     * Create a new route price
     * 
     * @param routePriceDTO the route price data transfer object
     * @return ResponseEntity with the created route price DTO and HTTP 201 Created
     *         status
     */
    @PostMapping
    public ResponseEntity<RoutePriceDTO> createRoutePrice(@RequestBody RoutePriceDTO routePriceDTO) {
        RoutePriceDTO createdRoutePrice = routePriceService.createRoutePrice(routePriceDTO);
        return new ResponseEntity<>(createdRoutePrice, HttpStatus.CREATED);
    }

    /**
     * Create a new route price for a specific agency
     * 
     * @param agencyId      the ID of the agency
     * @param routePriceDTO the route price data
     * @return ResponseEntity with the created route price DTO
     */
    @PostMapping("/agency/{agencyId}")
    public ResponseEntity<RoutePriceDTO> createRoutePriceScoped(@PathVariable String agencyId,
            @RequestBody RoutePriceDTO routePriceDTO) {
        RoutePriceDTO createdRoutePrice = routePriceService.createRoutePriceScoped(agencyId, routePriceDTO);
        return new ResponseEntity<>(createdRoutePrice, HttpStatus.CREATED);
    }

    /**
     * Get a route price by its ID
     * 
     * @param priceId the ID of the route price
     * @return ResponseEntity with the route price DTO if found, otherwise 404 Not
     *         Found
     */
    @GetMapping("/{priceId}")
    public ResponseEntity<RoutePriceDTO> getRoutePriceById(@PathVariable UUID priceId) {
        return routePriceService.getRoutePriceById(priceId)
                .map(price -> new ResponseEntity<>(price, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all route prices
     * 
     * @return ResponseEntity with list of all route price DTOs and HTTP 200 OK
     *         status
     */
    @GetMapping
    public ResponseEntity<List<RoutePriceDTO>> getAllRoutePrices() {
        List<RoutePriceDTO> routePrices = routePriceService.getAllRoutePrices();
        return new ResponseEntity<>(routePrices, HttpStatus.OK);
    }

    /**
     * Get all prices for a specific route
     * 
     * @param routeId the ID of the route
     * @return ResponseEntity with list of route price DTOs for the route
     */
    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<RoutePriceDTO>> getPricesByRoute(@PathVariable UUID routeId) {
        List<RoutePriceDTO> routePrices = routePriceService.getPricesByRoute(routeId);
        return new ResponseEntity<>(routePrices, HttpStatus.OK);
    }

    /**
     * Get all prices for a specific bus
     * 
     * @param busId the ID of the bus
     * @return ResponseEntity with list of route price DTOs for the bus
     */
    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<RoutePriceDTO>> getPricesByBus(@PathVariable UUID busId) {
        List<RoutePriceDTO> routePrices = routePriceService.getPricesByBus(busId);
        return new ResponseEntity<>(routePrices, HttpStatus.OK);
    }

    /**
     * Get all route prices for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @return ResponseEntity with list of route price DTOs for the agency
     */
    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<RoutePriceDTO>> getPricesByAgency(@PathVariable String agencyId) {
        List<RoutePriceDTO> routePrices = routePriceService.getPricesByAgency(agencyId);
        return new ResponseEntity<>(routePrices, HttpStatus.OK);
    }

    /**
     * Update an existing route price
     * 
     * @param priceId       the ID of the route price to update
     * @param routePriceDTO the updated route price data
     * @return ResponseEntity with the updated route price DTO if successful,
     *         otherwise 404 Not Found
     */
    @PutMapping("/{priceId}")
    public ResponseEntity<RoutePriceDTO> updateRoutePrice(@PathVariable UUID priceId,
            @RequestBody RoutePriceDTO routePriceDTO) {
        return routePriceService.updateRoutePrice(priceId, routePriceDTO)
                .map(price -> new ResponseEntity<>(price, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing route price for a specific agency
     * 
     * @param agencyId      the ID of the agency
     * @param priceId       the ID of the route price to update
     * @param routePriceDTO the updated route price data
     * @return ResponseEntity with the updated route price DTO
     */
    @PutMapping("/agency/{agencyId}/{priceId}")
    public ResponseEntity<RoutePriceDTO> updateRoutePriceScoped(@PathVariable String agencyId,
            @PathVariable UUID priceId, @RequestBody RoutePriceDTO routePriceDTO) {
        return routePriceService.updateRoutePriceScoped(agencyId, priceId, routePriceDTO)
                .map(price -> new ResponseEntity<>(price, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Delete a route price by its ID
     * 
     * @param priceId the ID of the route price to delete
     * @return ResponseEntity with HTTP 204 No Content if successful, otherwise 404
     *         Not Found
     */
    @DeleteMapping("/{priceId}")
    public ResponseEntity<Void> deleteRoutePrice(@PathVariable UUID priceId) {
        if (routePriceService.deleteRoutePrice(priceId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete a route price for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param priceId  the ID of the route price to delete
     * @return ResponseEntity with HTTP 204 No Content
     */
    @DeleteMapping("/agency/{agencyId}/{priceId}")
    public ResponseEntity<Void> deleteRoutePriceScoped(@PathVariable String agencyId, @PathVariable UUID priceId) {
        if (routePriceService.deleteRoutePriceScoped(agencyId, priceId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
