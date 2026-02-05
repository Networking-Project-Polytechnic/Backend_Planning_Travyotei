package com.example.agencyadmin.DTOs;

import java.util.UUID;

public class AssignmentsDTO {
    private UUID assignmentId;
    private UUID scheduleId;
    private UUID driverId;
    private UUID busId;
    private String agencyId;
    private String assignmentDate;

    public AssignmentsDTO() {
    }

    public AssignmentsDTO(UUID assignmentId, UUID scheduleId, UUID driverId, UUID busId, String agencyId,
            String assignmentDate) {
        this.assignmentId = assignmentId;
        this.scheduleId = scheduleId;
        this.driverId = driverId;
        this.busId = busId;
        this.agencyId = agencyId;
        this.assignmentDate = assignmentDate;
    }

    public UUID getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(UUID assignmentId) {
        this.assignmentId = assignmentId;
    }

    public UUID getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(UUID scheduleId) {
        this.scheduleId = scheduleId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public UUID getBusId() {
        return busId;
    }

    public void setBusId(UUID busId) {
        this.busId = busId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(String assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
}
