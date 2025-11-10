package com.example.agencyadmin.Mapper;

import com.example.agencyadmin.DTO.RouteDTO;
import com.example.agencyadmin.Models.Route;

public class RouteMapper {

    public static Route toEntity(RouteDTO dto){
        Route route = new Route();
        route.setStartlocationid(dto.getStartlocationid());
        route.setEndlocationid(dto.getEndlocationid());
        route.setAgencyid(dto.getAgencyid());
        return route;
        
    }
    
    public static RouteDTO toDto(Route route){
        RouteDTO dto = new RouteDTO();
        dto.setStartlocationid(route.getStartlocationid());
        dto.setEndlocationid(route.getEndlocationid());
        dto.setAgencyid(route.getAgencyid());
        return dto;
    }

    public static void updateEntityFromDto(RouteDTO dto, Route route) {
        route.setStartlocationid(dto.getStartlocationid());
        route.setEndlocationid(dto.getEndlocationid());
        route.setAgencyid(dto.getAgencyid());
    }
}
