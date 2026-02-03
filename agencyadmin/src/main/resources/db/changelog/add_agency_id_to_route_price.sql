-- Add agencyid column to route_price table
ALTER TABLE route_price ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);

-- Add index for faster lookups by agency
CREATE INDEX IF NOT EXISTS idx_route_price_agencyid ON route_price(agencyid);
