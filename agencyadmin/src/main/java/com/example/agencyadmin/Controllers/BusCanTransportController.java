package com.example.agencyadmin.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.DTOs.BusCanTransportDTO;
import com.example.agencyadmin.Services.BusCanTransportService;

@RestController
@RequestMapping("/api/bus-transportables")
public class BusCanTransportController {

    private final BusCanTransportService service;

    public BusCanTransportController(BusCanTransportService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BusCanTransportDTO>> getAllTransportables() {
        return ResponseEntity.ok(service.getAllTransportables());
    }

    @PostMapping
    public ResponseEntity<BusCanTransportDTO> createTransportable(@RequestBody BusCanTransportDTO dto) {
        return ResponseEntity.ok(service.createTransportable(dto));
    }
}
