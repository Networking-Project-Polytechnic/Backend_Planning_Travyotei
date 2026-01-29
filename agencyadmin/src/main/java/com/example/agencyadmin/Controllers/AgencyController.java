package com.example.agencyadmin.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.DTOs.AgencyOverviewDTO;
import com.example.agencyadmin.Services.AgencyService;

@RestController
@RequestMapping("/api/v1/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/{agencyId}/overview")
    public ResponseEntity<AgencyOverviewDTO> getAgencyOverview(
            @PathVariable String agencyId,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String date,
            @org.springframework.web.bind.annotation.RequestParam(required = false) String departureTime) {
        try {
            AgencyOverviewDTO overview = agencyService.getAgencyOverview(agencyId, date, departureTime);
            return ResponseEntity.ok(overview);
        } catch (IllegalArgumentException e) {
            // Likely invalid UUID format for agencyId if needed
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
