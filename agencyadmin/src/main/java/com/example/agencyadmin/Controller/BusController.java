package com.example.agencyadmin.Controller;

import java.util.List;
import java.util.Optional;
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

import com.example.agencyadmin.DTO.BusesDto;
import com.example.agencyadmin.Models.Buses;
import com.example.agencyadmin.Service.BusService;

@RestController
@RequestMapping("/api/buses")
public class BusController {
    private final BusService busService;

    public BusController(BusService busService){
        this.busService = busService;
    }
    
    @GetMapping
    public ResponseEntity<List<BusesDto>> getAllBuses(){
        List<BusesDto> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/{id}")
    public Optional<BusesDto> getBusById(@PathVariable UUID id){
        return busService.getBusById(id);
    }

    @PostMapping
    public ResponseEntity<Buses> createBus(@RequestBody BusesDto dto) {
        Buses created = busService.createBus(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buses> updateBus(UUID id, @RequestBody BusesDto dto) {
        Buses updated = busService.updateBus(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable UUID id) {
        busService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }

    //add a seperate entity for bus types.


}
