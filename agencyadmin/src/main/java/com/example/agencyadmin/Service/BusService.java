package com.example.agencyadmin.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTO.BusesDto;
import com.example.agencyadmin.Mapper.BusMapper;
import com.example.agencyadmin.Models.Buses;
import com.example.agencyadmin.Repositories.BusRepo;

@Service
public class BusService {
    
    private final BusRepo busesRepository;

    public BusService(BusRepo busesRepository) {
        this.busesRepository = busesRepository;
    }



   public List<BusesDto> getAllBuses() {
        return busesRepository.findAll()
                .stream()
                .map(BusMapper::toDto)
                .toList();
    }

    public Optional<BusesDto> getBusById(UUID id) {
        return busesRepository.findById(id)
                .map(BusMapper::toDto);
    }


    public Buses createBus(BusesDto dto) {
        if (busesRepository.existsByBusplatenumber(dto.getBusplatenumber())) {
            throw new RuntimeException("Bus with this plate number already exists");
        }
        Buses bus = BusMapper.toEntity(dto);
        return busesRepository.save(bus);
    }

    public Buses updateBus(UUID id, BusesDto dto) {
        return busesRepository.findById(id)
                .map(bus -> {
                    BusMapper.updateEntityFromDto(dto, bus);
                    return busesRepository.save(bus);
                })
                .orElseThrow(() -> new RuntimeException("Bus not found"));
    }

    public void deleteBus(UUID id) {
        busesRepository.deleteById(id);
    }
    
}
