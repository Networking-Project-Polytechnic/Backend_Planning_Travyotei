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
import com.example.agencyadmin.Models.Schedule;
import com.example.agencyadmin.Repositories.AssignmentsRepository;
import com.example.agencyadmin.Repositories.ScheduleRepository;

@Service
public class AssignmentsService {

    @Autowired
    private AssignmentsRepository assignmentsRepository;

    @Autowired
    private AssignmentsMapper assignmentsMapper;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public AssignmentsDTO createAssignment(AssignmentsDTO assignmentsDTO) {
        Assignments assignments = assignmentsMapper.toEntity(assignmentsDTO);
        assignments.setAssignmentId(null); // Ensure it's treated as a new entity

        // Auto-fill busId from schedule if not provided
        if (assignments.getBusId() == null && assignments.getScheduleId() != null) {
            scheduleRepository.findById(assignments.getScheduleId()).ifPresent(schedule -> {
                assignments.setBusId(schedule.getBusid());
            });
        }

        Assignments savedAssignment = assignmentsRepository.save(assignments);
        return assignmentsMapper.toDTO(savedAssignment);
    }

    /**
     * Create a new assignment for a specific agency
     * 
     * @param agencyId       the ID of the agency
     * @param assignmentsDTO the assignment data
     * @return the created assignment DTO
     */
    public AssignmentsDTO createAssignmentScoped(String agencyId, AssignmentsDTO assignmentsDTO) {
        assignmentsDTO.setAgencyId(agencyId);
        return createAssignment(assignmentsDTO);
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

            // Auto-fill or update busId
            if (assignmentsDTO.getBusId() != null) {
                existingAssignment.setBusId(assignmentsDTO.getBusId());
            } else if (existingAssignment.getScheduleId() != null) {
                scheduleRepository.findById(existingAssignment.getScheduleId()).ifPresent(schedule -> {
                    existingAssignment.setBusId(schedule.getBusid());
                });
            }

            existingAssignment.setAgencyId(assignmentsDTO.getAgencyId());
            existingAssignment.setAssignmentDate(assignmentsDTO.getAssignmentDate());
            Assignments updatedAssignment = assignmentsRepository.save(existingAssignment);
            return assignmentsMapper.toDTO(updatedAssignment);
        });
    }

    /**
     * Update an existing assignment for a specific agency
     * 
     * @param agencyId       the ID of the agency
     * @param assignmentId   the ID of the assignment to update
     * @param assignmentsDTO the updated assignment data
     * @return the updated assignment DTO if successful
     */
    public Optional<AssignmentsDTO> updateAssignmentScoped(String agencyId, UUID assignmentId,
            AssignmentsDTO assignmentsDTO) {
        Optional<Assignments> existingAssignment = assignmentsRepository.findById(assignmentId);
        if (existingAssignment.isPresent() && existingAssignment.get().getAgencyId().equals(agencyId)) {
            assignmentsDTO.setAgencyId(agencyId); // Ensure agencyId remains correct
            return updateAssignment(assignmentId, assignmentsDTO);
        }
        return Optional.empty();
    }

    public boolean deleteAssignment(UUID assignmentId) {
        if (assignmentsRepository.existsById(assignmentId)) {
            assignmentsRepository.deleteById(assignmentId);
            return true;
        }
        return false;
    }

    /**
     * Delete an assignment for a specific agency
     * 
     * @param agencyId     the ID of the agency
     * @param assignmentId the ID of the assignment to delete
     * @return true if deletion was successful
     */
    public boolean deleteAssignmentScoped(String agencyId, UUID assignmentId) {
        Optional<Assignments> existingAssignment = assignmentsRepository.findById(assignmentId);
        if (existingAssignment.isPresent() && existingAssignment.get().getAgencyId().equals(agencyId)) {
            return deleteAssignment(assignmentId);
        }
        return false;
    }
}
