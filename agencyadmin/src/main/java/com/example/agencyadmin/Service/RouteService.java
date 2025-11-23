package com.example.agencyadmin.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTO.RouteDTO;
import com.example.agencyadmin.Mapper.RouteMapper;
import com.example.agencyadmin.Models.Route;
import com.example.agencyadmin.Repositories.RouteRepo;
@Service
public class RouteService {
    private final RouteRepo routeRepo;

    public RouteService(RouteRepo routeRepo){
        this.routeRepo = routeRepo;
    }

    public List<RouteDTO> getAllRoutes(){
        return routeRepo.findAll()
               .stream()
               .map(RouteMapper::toDto)
               .collect(Collectors.toList());
    }

    public List<RouteDTO> getAllRoutesByAgencyId(String agencyid){
        return routeRepo.findByAgencyid(agencyid)
               .stream()
               .map(RouteMapper::toDto)
                .collect(Collectors.toList());
    }

    public Route createRoute(RouteDTO dto){
        if(routeRepo.existsByAgencyidAndStartlocationidAndEndlocationid(dto.getAgencyid(),dto.getStartlocationid(),dto.getEndlocationid())){
             throw new RuntimeException("Route already exists");
        }
        return routeRepo.save(RouteMapper.toEntity(dto));
    }

    public Route updateRoute(UUID id, RouteDTO dto){
        return routeRepo.findById(id)
        .map(route -> {
            RouteMapper.updateEntityFromDto(dto, route);
            return routeRepo.save(route);
        })
        .orElseThrow(()-> new RuntimeException("Route not Found"));
    }
    public void deleteRoute(UUID id){
        routeRepo.deleteById(id);
    }
}
