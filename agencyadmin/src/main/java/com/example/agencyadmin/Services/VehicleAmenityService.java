package com.example.agencyadmin.Services;

import java.util.List;
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

    public VehicleAmenityDTO createAmenity(VehicleAmenityDTO dto) {
        VehicleAmenity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }
}
