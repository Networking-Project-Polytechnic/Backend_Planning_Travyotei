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

    /**
     * Create a new vehicle amenity for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param dto      the amenity data
     * @return the created amenity DTO
     */
    public VehicleAmenityDTO createAmenityScoped(String agencyId, VehicleAmenityDTO dto) {
        dto.setAgencyId(agencyId);
        return createAmenity(dto);
    }

    public Optional<VehicleAmenityDTO> updateAmenity(UUID id, VehicleAmenityDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setAmenityName(dto.getAmenityName());
            existing.setDescription(dto.getDescription());
            existing.setAgencyid(dto.getAgencyId());
            return mapper.toDTO(repository.save(existing));
        });
    }

    /**
     * Update an existing vehicle amenity for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param id       the ID of the amenity to update
     * @param dto      the updated amenity data
     * @return the updated amenity DTO if successful
     */
    public Optional<VehicleAmenityDTO> updateAmenityScoped(String agencyId, UUID id, VehicleAmenityDTO dto) {
        Optional<VehicleAmenity> existing = repository.findById(id);
        if (existing.isPresent() && existing.get().getAgencyid().equals(agencyId)) {
            dto.setAgencyId(agencyId); // Ensure agencyId remains correct
            return updateAmenity(id, dto);
        }
        return Optional.empty();
    }

    public boolean deleteAmenity(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Delete a vehicle amenity for a specific agency
     * 
     * @param agencyId the ID of the agency
     * @param id       the ID of the amenity to delete
     * @return true if deletion was successful
     */
    public boolean deleteAmenityScoped(String agencyId, UUID id) {
        Optional<VehicleAmenity> existing = repository.findById(id);
        if (existing.isPresent() && existing.get().getAgencyid().equals(agencyId)) {
            return deleteAmenity(id);
        }
        return false;
    }
}
