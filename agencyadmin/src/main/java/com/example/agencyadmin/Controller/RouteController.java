package com.example.agencyadmin.Controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.DTO.RouteDTO;
import com.example.agencyadmin.Models.Route;
import com.example.agencyadmin.Service.RouteService;

@RestController
@RequestMapping("/api/routes")
public class RouteController {
    private final RouteService RouteService;

    public RouteController(RouteService RouteService){
        this.RouteService = RouteService;
    }

    @GetMapping
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {
        List<RouteDTO> routes = RouteService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }
    @GetMapping("/{agencyId}")
    public ResponseEntity<List<RouteDTO>> getroutesByAgencyId(@PathVariable String agencyId) {
        List<RouteDTO> routes = RouteService.getAllRoutesByAgencyId(agencyId);

        return ResponseEntity.ok(routes);
    }
    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody RouteDTO RouteDTO) {
        Route createdRoute = RouteService.createRoute(RouteDTO);
        return ResponseEntity.ok(createdRoute);
    }
    @PutMapping("/{routeid}")
    public ResponseEntity<Route> updateRoute( @PathVariable UUID routeid, @RequestBody RouteDTO RouteDTO) {
        Route updatedRoute = RouteService.updateRoute(routeid, RouteDTO);
        return ResponseEntity.ok(updatedRoute);
    }   
    @DeleteMapping("/{routeid}")
    public ResponseEntity<Void> deleteFair(@PathVariable UUID routeid) {
        RouteService.deleteRoute(routeid);
        return ResponseEntity.noContent().build();
    }
}
