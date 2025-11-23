package com.example.agencyadmin.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTO.ScheduleDTO;
import com.example.agencyadmin.Mapper.ScheduleMapper;
import com.example.agencyadmin.Models.Schedule;
import com.example.agencyadmin.Repositories.ScheduleRepo;

@Service
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;
   

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
      
    }

    public List<ScheduleDTO> getSchedulesByAgencyId(String agencyId) {
        return scheduleRepo.findByAgencyid(agencyId)
                           .stream()
                           .map(ScheduleMapper::toDto)
                           .collect(Collectors.toList());
    }

    public ScheduleDTO createSchedule(ScheduleDTO dto) {
        Schedule schedule = ScheduleMapper.toEntity(dto);
        Schedule saved = scheduleRepo.save(schedule);
        return ScheduleMapper.toDto(saved);
    }

    public ScheduleDTO updateSchedule(UUID scheduleId, ScheduleDTO dto) {
        Schedule existing = scheduleRepo.findById(scheduleId)
            .orElseThrow(() -> new RuntimeException("Schedule not found"));

        ScheduleMapper.updateEntityFromDto(dto, existing);
        Schedule saved = scheduleRepo.save(existing);

        return ScheduleMapper.toDto(saved);
    }

    public void deleteSchedule(UUID scheduleId) {
        scheduleRepo.deleteById(scheduleId);
    }
}
