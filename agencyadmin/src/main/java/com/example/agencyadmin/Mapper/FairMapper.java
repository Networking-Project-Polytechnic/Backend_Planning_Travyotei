package com.example.agencyadmin.Mapper;

import com.example.agencyadmin.DTO.FairDTO;
import com.example.agencyadmin.Models.Fair;

public class FairMapper {
    public static Fair toEntity(FairDTO dto){
        Fair fair  = new Fair();
        fair.setFairamount(dto.getFairamount());
        fair.setAgencyid(dto.getAgencyid());
        return fair;
    }

    public static FairDTO toDto(Fair fair){
        FairDTO dto = new FairDTO();
        dto.setFairamount(fair.getFairamount());
        dto.setAgencyid(fair.getAgencyid());
        return dto;
    }

    public static void updateEntityFromDto(FairDTO dto, Fair fair) {
        fair.setFairamount(dto.getFairamount());
        fair.setAgencyid(dto.getAgencyid());
    }

    
}
