package com.example.agencyadmin.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTO.LocationDTO;
import com.example.agencyadmin.Mapper.LocationMapper;
import com.example.agencyadmin.Models.Location;
import com.example.agencyadmin.Repositories.LocationRepo;
@Service
public class LocationService {

    private final LocationRepo locationRepo;

    public LocationService(LocationRepo locationRepo){
        this.locationRepo = locationRepo;
    }

    public List<LocationDTO> getAllLocations(){
        return locationRepo.findAll()
               .stream()
               .map(LocationMapper::toDto)
               .collect(Collectors.toList());
    }

    public List<LocationDTO> getAllLocationsByAgencyid(String agencyid){
        return locationRepo.findByAgencyid(agencyid)
               .stream()
               .map(LocationMapper::toDto)
                .collect(Collectors.toList());

    }
    public Location createLocation(LocationDTO dto){
        if(locationRepo.existsByAgencyidAndLocationname(dto.getAgencyid(),dto.getLocationname())){
             throw new RuntimeException("Location name already exists");
        }
        Location location = LocationMapper.toEntity(dto);
        return locationRepo.save(location);
    }
    
    public Location updateLocation(UUID id, LocationDTO dto){
        return locationRepo.findById(id)
        .map(location -> {
            LocationMapper.updateEntityFromDto(dto, location);
            return locationRepo.save(location);
        })
        .orElseThrow(()-> new RuntimeException("Location not Found"));
    }

    public void deleteLocation(UUID id){
        locationRepo.deleteById(id);
    }
}
