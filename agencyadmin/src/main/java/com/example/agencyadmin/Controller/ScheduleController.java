package com.example.agencyadmin.Controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.DTO.ScheduleDTO;
import com.example.agencyadmin.Service.ScheduleService;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByAgencyId(@PathVariable String agencyId) {
        return ResponseEntity.ok(
            scheduleService.getSchedulesByAgencyId(agencyId)
        );
    }

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDto) {
        ScheduleDTO created = scheduleService.createSchedule(scheduleDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleDTO> updateSchedule(
            @PathVariable UUID scheduleId,
            @RequestBody ScheduleDTO scheduleDto
    ) {
        ScheduleDTO updated = scheduleService.updateSchedule(scheduleId, scheduleDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable UUID scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
