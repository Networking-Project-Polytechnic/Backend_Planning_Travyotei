package com.example.agencyadmin.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTO.FairDTO;
import com.example.agencyadmin.Mapper.FairMapper;
import com.example.agencyadmin.Models.Fair;
import com.example.agencyadmin.Repositories.FairRepo;

@Service
public class FairService {
    private final FairRepo fairRepo;

    public FairService(FairRepo fairRepo){
        this.fairRepo = fairRepo;
    }
    
    public List<FairDTO> getAllFairs(){
        return fairRepo.findAll()
               .stream()  
               .map(FairMapper::toDto)
               .collect(Collectors.toList());
    }

    public List<FairDTO> getFairByAgencyId(String agencyid ){
        return fairRepo.findByAgencyid(agencyid)
               .stream()
               .map(FairMapper::toDto)
               .collect(Collectors.toList());
    }

    public Fair createFair(FairDTO dto){
        if(fairRepo.existsByAgencyidAndFairamount(dto.getAgencyid(),dto.getFairamount())){
             throw new RuntimeException("Fair amount already exists");
        }
        Fair fair = FairMapper.toEntity(dto);
        return fairRepo.save(fair);
    }
    public Fair updateFair(UUID id, FairDTO dto){
        return fairRepo.findById(id)
        .map(fair -> {
            FairMapper.updateEntityFromDto(dto, fair);
            return fairRepo.save(fair);
        })
        .orElseThrow(()-> new RuntimeException("Fair not Found"));
    }

    public void deleteFair(UUID id){
        fairRepo.deleteById(id);
    }
    
}
