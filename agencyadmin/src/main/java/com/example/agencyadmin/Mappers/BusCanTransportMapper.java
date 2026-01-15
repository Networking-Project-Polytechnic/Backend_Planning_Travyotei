package com.example.agencyadmin.Mappers;

import org.springframework.stereotype.Component;
import com.example.agencyadmin.DTOs.BusCanTransportDTO;
import com.example.agencyadmin.Models.BusCanTransport;

@Component
public class BusCanTransportMapper {

    public BusCanTransportDTO toDTO(BusCanTransport entity) {
        if (entity == null) {
            return null;
        }
        BusCanTransportDTO dto = new BusCanTransportDTO();
        dto.setTransportId(entity.getTransportId());
        dto.setItemName(entity.getItemName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public BusCanTransport toEntity(BusCanTransportDTO dto) {
        if (dto == null) {
            return null;
        }
        BusCanTransport entity = new BusCanTransport();
        entity.setTransportId(dto.getTransportId());
        entity.setItemName(dto.getItemName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
