-- Add agencyid column to driver table
ALTER TABLE driver ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);

-- Add index for faster lookups by agency
CREATE INDEX IF NOT EXISTS idx_driver_agencyid ON driver(agencyid);
