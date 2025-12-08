package com.example.agencyadmin.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.DTO.AgencyDTO;
import com.example.agencyadmin.Service.AgencyService;

@RestController
@RequestMapping("api/agencyschedules")
public class AgencyController {
    private final AgencyService agencyService;

    public AgencyController(AgencyService agencyService) {
        this.agencyService = agencyService;
    }
    
    @GetMapping("/{date}/{routeId}/{agencyId}")
    public List<AgencyDTO> getSchedules(
            @PathVariable String date,
            @PathVariable UUID routeId,
            @PathVariable String agencyId
    ) {
        System.out.println(agencyId);
        System.out.println(date);
        System.out.println(routeId);
        return agencyService.getFilteredSchedules(date, routeId, agencyId);
    }
    
}
