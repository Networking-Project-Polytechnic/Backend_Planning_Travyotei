package com.example.agencyadmin.Mappers;

import org.springframework.stereotype.Component;
import com.example.agencyadmin.DTOs.AssignmentsDTO;
import com.example.agencyadmin.Models.Assignments;

@Component
public class AssignmentsMapper {

    public AssignmentsDTO toDTO(Assignments assignments) {
        if (assignments == null) {
            return null;
        }
        return new AssignmentsDTO(
                assignments.getAssignmentId(),
                assignments.getScheduleId(),
                assignments.getDriverId(),
                assignments.getBusId(),
                assignments.getAgencyId(),
                assignments.getAssignmentDate());
    }

    public Assignments toEntity(AssignmentsDTO assignmentsDTO) {
        if (assignmentsDTO == null) {
            return null;
        }
        Assignments assignments = new Assignments();
        assignments.setAssignmentId(assignmentsDTO.getAssignmentId());
        assignments.setScheduleId(assignmentsDTO.getScheduleId());
        assignments.setDriverId(assignmentsDTO.getDriverId());
        assignments.setBusId(assignmentsDTO.getBusId());
        assignments.setAgencyId(assignmentsDTO.getAgencyId());
        assignments.setAssignmentDate(assignmentsDTO.getAssignmentDate());
        return assignments;
    }
}
