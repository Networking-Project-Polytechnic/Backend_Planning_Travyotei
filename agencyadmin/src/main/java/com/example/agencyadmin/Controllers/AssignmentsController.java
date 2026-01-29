package com.example.agencyadmin.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.agencyadmin.DTOs.AssignmentsDTO;
import com.example.agencyadmin.Services.AssignmentsService;

@RestController
@RequestMapping("/api/v1/assignments")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssignmentsController {

    @Autowired
    private AssignmentsService assignmentsService;

    @PostMapping
    public ResponseEntity<AssignmentsDTO> createAssignment(@RequestBody AssignmentsDTO assignmentsDTO) {
        AssignmentsDTO createdAssignment = assignmentsService.createAssignment(assignmentsDTO);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    @GetMapping("/agency/{agencyId}")
    public ResponseEntity<List<AssignmentsDTO>> getAssignmentsByAgency(@PathVariable String agencyId) {
        return ResponseEntity.ok(assignmentsService.getAssignmentsByAgency(agencyId));
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<AssignmentsDTO> getAssignmentById(@PathVariable UUID assignmentId) {
        return assignmentsService.getAssignmentById(assignmentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AssignmentsDTO>> getAllAssignments() {
        return ResponseEntity.ok(assignmentsService.getAllAssignments());
    }

    @org.springframework.web.bind.annotation.PutMapping("/{assignmentId}")
    public ResponseEntity<AssignmentsDTO> updateAssignment(@PathVariable UUID assignmentId,
            @RequestBody AssignmentsDTO assignmentsDTO) {
        return assignmentsService.updateAssignment(assignmentId, assignmentsDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable UUID assignmentId) {
        if (assignmentsService.deleteAssignment(assignmentId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
