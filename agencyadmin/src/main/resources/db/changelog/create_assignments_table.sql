-- ========================
-- Assignments Table
-- ========================

CREATE TABLE IF NOT EXISTS assignments (
    assignment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    schedule_id UUID NOT NULL,
    driver_id UUID NOT NULL,
    agencyid VARCHAR(50) NOT NULL,
    assignment_date VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_assignments_schedule FOREIGN KEY (schedule_id) REFERENCES schedule(scheduleid) ON DELETE CASCADE,
    CONSTRAINT fk_assignments_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE
);

CREATE INDEX idx_assignments_agencyid ON assignments(agencyid);
CREATE INDEX idx_assignments_schedule_id ON assignments(schedule_id);
CREATE INDEX idx_assignments_driver_id ON assignments(driver_id);
