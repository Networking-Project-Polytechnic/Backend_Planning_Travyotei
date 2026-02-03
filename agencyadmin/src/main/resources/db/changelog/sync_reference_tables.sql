-- Syncing reference tables with agencyid column to match Java entities

-- 1. fuel_type
ALTER TABLE fuel_type ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);
CREATE INDEX IF NOT EXISTS idx_fuel_type_agencyid ON fuel_type(agencyid);

-- 2. transmission_type
ALTER TABLE transmission_type ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);
CREATE INDEX IF NOT EXISTS idx_transmission_type_agencyid ON transmission_type(agencyid);

-- 3. bus_type
ALTER TABLE bus_type ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);
CREATE INDEX IF NOT EXISTS idx_bus_type_agencyid ON bus_type(agencyid);

-- 4. bus_manufacturer
ALTER TABLE bus_manufacturer ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);
CREATE INDEX IF NOT EXISTS idx_bus_manufacturer_agencyid ON bus_manufacturer(agencyid);

-- 5. bus_make
ALTER TABLE bus_make ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);
CREATE INDEX IF NOT EXISTS idx_bus_make_agencyid ON bus_make(agencyid);

-- 6. bus_model
ALTER TABLE bus_model ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);
CREATE INDEX IF NOT EXISTS idx_bus_model_agencyid ON bus_model(agencyid);

-- 7. vehicle_amenity
ALTER TABLE vehicle_amenity ADD COLUMN IF NOT EXISTS agencyid VARCHAR(255);
CREATE INDEX IF NOT EXISTS idx_vehicle_amenity_agencyid ON vehicle_amenity(agencyid);
