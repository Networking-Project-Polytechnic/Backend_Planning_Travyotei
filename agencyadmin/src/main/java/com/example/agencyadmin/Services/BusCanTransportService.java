package com.example.agencyadmin.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTOs.BusCanTransportDTO;
import com.example.agencyadmin.Mappers.BusCanTransportMapper;
import com.example.agencyadmin.Models.BusCanTransport;
import com.example.agencyadmin.Repositories.BusCanTransportRepository;

@Service
public class BusCanTransportService {

    private final BusCanTransportRepository repository;
    private final BusCanTransportMapper mapper;

    public BusCanTransportService(BusCanTransportRepository repository, BusCanTransportMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BusCanTransportDTO> getAllTransportables() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public BusCanTransportDTO createTransportable(BusCanTransportDTO dto) {
        BusCanTransport entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }
}
