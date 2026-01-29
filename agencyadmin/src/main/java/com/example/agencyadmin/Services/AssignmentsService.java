package com.example.agencyadmin.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.agencyadmin.DTOs.AssignmentsDTO;
import com.example.agencyadmin.Mappers.AssignmentsMapper;
import com.example.agencyadmin.Models.Assignments;
import com.example.agencyadmin.Repositories.AssignmentsRepository;

@Service
public class AssignmentsService {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Autowired
    private AssignmentsMapper assignmentsMapper;

    public AssignmentsDTO createAssignment(AssignmentsDTO assignmentsDTO) {
        Assignments assignments = assignmentsMapper.toEntity(assignmentsDTO);
        Assignments savedAssignment = assignmentsRepository.save(assignments);
        return assignmentsMapper.toDTO(savedAssignment);
    }

    public List<AssignmentsDTO> getAssignmentsByAgency(String agencyId) {
        return assignmentsRepository.findByAgencyId(agencyId).stream()
                .map(assignmentsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AssignmentsDTO> getAssignmentById(UUID assignmentId) {
        return assignmentsRepository.findById(assignmentId)
                .map(assignmentsMapper::toDTO);
    }

    public List<AssignmentsDTO> getAllAssignments() {
        return assignmentsRepository.findAll().stream()
                .map(assignmentsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AssignmentsDTO> updateAssignment(UUID assignmentId, AssignmentsDTO assignmentsDTO) {
        return assignmentsRepository.findById(assignmentId).map(existingAssignment -> {
            existingAssignment.setScheduleId(assignmentsDTO.getScheduleId());
            existingAssignment.setDriverId(assignmentsDTO.getDriverId());
            existingAssignment.setAgencyId(assignmentsDTO.getAgencyId());
            existingAssignment.setAssignmentDate(assignmentsDTO.getAssignmentDate());
            Assignments updatedAssignment = assignmentsRepository.save(existingAssignment);
            return assignmentsMapper.toDTO(updatedAssignment);
        });
    }

    public boolean deleteAssignment(UUID assignmentId) {
        if (assignmentsRepository.existsById(assignmentId)) {
            assignmentsRepository.deleteById(assignmentId);
            return true;
        }
        return false;
    }
}
