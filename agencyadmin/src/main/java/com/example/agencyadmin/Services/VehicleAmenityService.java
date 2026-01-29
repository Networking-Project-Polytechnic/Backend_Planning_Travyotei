package com.example.agencyadmin.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTOs.VehicleAmenityDTO;
import com.example.agencyadmin.Mappers.VehicleAmenityMapper;
import com.example.agencyadmin.Models.VehicleAmenity;
import com.example.agencyadmin.Repositories.VehicleAmenityRepository;

@Service
public class VehicleAmenityService {

    private final VehicleAmenityRepository repository;
    private final VehicleAmenityMapper mapper;

    public VehicleAmenityService(VehicleAmenityRepository repository, VehicleAmenityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<VehicleAmenityDTO> getAllAmenities() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<VehicleAmenityDTO> getAmenitiesByAgency(String agencyId) {
        return repository.findByAgencyid(agencyId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<VehicleAmenityDTO> getAmenityById(UUID id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public VehicleAmenityDTO createAmenity(VehicleAmenityDTO dto) {
        VehicleAmenity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    public Optional<VehicleAmenityDTO> updateAmenity(UUID id, VehicleAmenityDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setAmenityName(dto.getAmenityName());
            existing.setDescription(dto.getDescription());
            existing.setAgencyid(dto.getAgencyId());
            return mapper.toDTO(repository.save(existing));
        });
    }

    public boolean deleteAmenity(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
