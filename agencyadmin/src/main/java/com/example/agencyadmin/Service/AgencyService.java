package com.example.agencyadmin.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTO.AgencyDTO;
import com.example.agencyadmin.Mapper.AgencyMapper;
import com.example.agencyadmin.Repositories.AgencyRepo;

@Service
public class AgencyService {
    private final AgencyRepo agencyRepo;

    public AgencyService(AgencyRepo agencyRepo){
        this.agencyRepo = agencyRepo;
    }
    public List<AgencyDTO> getFilteredSchedules(String date, UUID routeId, String agencyId) {
        System.out.println(agencyId);
        System.out.println(date);
        System.out.println(routeId);
            List<Object[]> rows = agencyRepo.filterSchedule(routeId, agencyId, date);
            System.out.println(rows);
            return rows.stream()
                    .map(AgencyMapper::map)
                    .toList();
    }
    
}
