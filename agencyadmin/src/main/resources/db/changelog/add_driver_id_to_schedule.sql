-- Add driverid column to schedule table
ALTER TABLE schedule ADD COLUMN IF NOT EXISTS driverid UUID;

-- Add foreign key constraint
ALTER TABLE schedule 
ADD CONSTRAINT fk_schedule_driver 
FOREIGN KEY (driverid) REFERENCES driver(driver_id) 
ON DELETE SET NULL;

-- Add index for performance
CREATE INDEX IF NOT EXISTS idx_schedule_driver_id ON schedule(driverid);
