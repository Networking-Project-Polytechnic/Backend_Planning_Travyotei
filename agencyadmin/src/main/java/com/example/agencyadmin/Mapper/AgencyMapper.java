package com.example.agencyadmin.Mapper;


import java.util.UUID;

import com.example.agencyadmin.DTO.AgencyDTO;


public class AgencyMapper {
    //Entity to dto
     public static AgencyDTO map(Object[] row) {
        AgencyDTO dto = new AgencyDTO();
        dto.setScheduleid((UUID) row[0]);
        dto.setDeparturetime(row[1].toString());
        dto.setArrivaltime(row[2].toString());
        dto.setBuscapacity(((Number) row[3]).intValue());
        dto.setFairamount(((Number) row[4]).floatValue());
        return dto;

    }
    
}
