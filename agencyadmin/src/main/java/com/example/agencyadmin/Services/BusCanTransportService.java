package com.example.agencyadmin.Services;

import java.util.List;
import java.util.UUID;
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

    public List<BusCanTransportDTO> getTransportablesByAgency(String agencyId) {
        return repository.findByAgencyid(agencyId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public java.util.Optional<BusCanTransportDTO> getTransportableById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public BusCanTransportDTO createTransportable(BusCanTransportDTO dto) {
        BusCanTransport entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public java.util.Optional<BusCanTransportDTO> updateTransportable(UUID id, BusCanTransportDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setItemName(dto.getItemName());
            existing.setDescription(dto.getDescription());
            existing.setAgencyid(dto.getAgencyId());
            return mapper.toDTO(repository.save(existing));
        });
    }

    public boolean deleteTransportable(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
