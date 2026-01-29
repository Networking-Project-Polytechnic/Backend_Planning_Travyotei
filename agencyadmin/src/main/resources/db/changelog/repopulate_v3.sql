-- =============================================
-- Repopulate V3: Consistent Data for Frontend Testing
-- =============================================

-- 1. TRUNCATE ALL TABLES
-- Using CASCADE to handle foreign key dependencies automatically
TRUNCATE TABLE 
    assignments, 
    schedule, 
    route_price, 
    route_stop_points,
    route, 
    bus_amenities,
    bus_review,
    bus_image,
    driver_image,
    bus, 
    driver, 
    bus_model, 
    bus_make, 
    bus_manufacturer, 
    vehicle_amenity, 
    bus_type, 
    fuel_type, 
    transmission_type, 
    location 
CASCADE;

--Constants
-- Agency ID: 'test-agency-id' (Used commonly as string in some tables)
-- Common UUID prefixes:
-- 1000... Fuel
-- 2000... Transmission
-- 3000... BusType
-- 4000... Manufacturer
-- 5000... Amenity
-- 6000... Location
-- 7000... Make
-- 8000... Model
-- 9000... Driver
-- A000... Bus
-- B000... Route
-- C000... Price
-- D000... Schedule
-- E000... Assignment

-- 2. REFERENCE DATA

-- Fuel Types
INSERT INTO fuel_type (fuel_type_id, fuel_type_name) VALUES 
('10000000-0000-0000-0000-000000000001', 'Diesel'),
('10000000-0000-0000-0000-000000000002', 'Petrol'),
('10000000-0000-0000-0000-000000000003', 'Electric');

-- Transmission Types
INSERT INTO transmission_type (transmission_type_id, type_name) VALUES
('20000000-0000-0000-0000-000000000001', 'Manual'),
('20000000-0000-0000-0000-000000000002', 'Automatic');

-- Bus Types (Note: schema uses camelCase for columns "busTypeId" and "busTypeName" in some versions, but "rename_columns.sql" might have fixed it. 
-- Based on the previous view of data.sql, it used quoted identifiers. However, repopulate_v2.sql used snake_case. 
-- I will assume snake_case if rename_columns.sql ran effectively. If not, this might fail, but let's stick to standard SQL first as per rename_columns.sql which renames them to snake_case.)
INSERT INTO bus_type (bus_type_id, bus_type_name) VALUES
('30000000-0000-0000-0000-000000000001', 'Standard'),
('30000000-0000-0000-0000-000000000002', 'Luxury'),
('30000000-0000-0000-0000-000000000003', 'Sleeper');

-- Bus Manufacturers
INSERT INTO bus_manufacturer (manufacturer_id, manufacturer_name) VALUES
('40000000-0000-0000-0000-000000000001', 'Mercedes-Benz'),
('40000000-0000-0000-0000-000000000002', 'Volvo');

-- Bus Makes
INSERT INTO bus_make (bus_make_id, make_name) VALUES
('70000000-0000-0000-0000-000000000001', 'Sprinter'),
('70000000-0000-0000-0000-000000000002', '9900');

-- Bus Models
INSERT INTO bus_model (bus_model_id, model_name) VALUES
('80000000-0000-0000-0000-000000000001', '2024 Edition'),
('80000000-0000-0000-0000-000000000002', 'Classic');

-- Vehicle Amenities
INSERT INTO vehicle_amenity (amenity_id, amenity_name, description) VALUES
('50000000-0000-0000-0000-000000000001', 'Wi-Fi', 'High speed internet'),
('50000000-0000-0000-0000-000000000002', 'AC', 'Air Conditioning'),
('50000000-0000-0000-0000-000000000003', 'USB Charging', 'Charging ports at every seat');

-- Locations (5 consistent locations)
INSERT INTO location (locationid, locationname, agencyid) VALUES
('60000000-0000-0000-0000-000000000001', 'Central Station', 'test-agency-id'),
('60000000-0000-0000-0000-000000000002', 'Airport Terminal', 'test-agency-id'),
('60000000-0000-0000-0000-000000000003', 'North Plaza', 'test-agency-id'),
('60000000-0000-0000-0000-000000000004', 'South Beach', 'test-agency-id'),
('60000000-0000-0000-0000-000000000005', 'East Market', 'test-agency-id');

-- 3. MAIN ENTITIES

-- Drivers
INSERT INTO driver (driver_id, full_name, phone, license_number, agencyid) VALUES
('90000000-0000-0000-0000-000000000001', 'John Doe', '555-1001', 'LIC-1001', 'test-agency-id'),
('90000000-0000-0000-0000-000000000002', 'Jane Smith', '555-1002', 'LIC-1002', 'test-agency-id'),
('90000000-0000-0000-0000-000000000003', 'Robert Johnson', '555-1003', 'LIC-1003', 'test-agency-id'),
('90000000-0000-0000-0000-000000000004', 'Maria Garcia', '555-1004', 'LIC-1004', 'test-agency-id'),
('90000000-0000-0000-0000-000000000005', 'James Wilson', '555-1005', 'LIC-1005', 'test-agency-id');

-- Buses
INSERT INTO bus (bus_id, agency_id, bus_make_id, bus_model_id, manufacturer_id, fuel_type_id, transmission_type_id, bus_type_id, registration_number, total_seats, luggage_capacity_kg) VALUES
-- Bus 1: Mercedes Sprinter (Standard)
('A0000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000000', '70000000-0000-0000-0000-000000000001', '80000000-0000-0000-0000-000000000001', '40000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000001', '30000000-0000-0000-0000-000000000001', 'BUS-001', 20, 500.0),
-- Bus 2: Volvo 9900 (Luxury)
('A0000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000000', '70000000-0000-0000-0000-000000000002', '80000000-0000-0000-0000-000000000001', '40000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000002', '30000000-0000-0000-0000-000000000002', 'BUS-002', 50, 1000.0),
-- Bus 3: Mercedes Sprinter (Standard)
('A0000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000000', '70000000-0000-0000-0000-000000000001', '80000000-0000-0000-0000-000000000001', '40000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000002', '20000000-0000-0000-0000-000000000001', '30000000-0000-0000-0000-000000000001', 'BUS-003', 20, 500.0),
-- Bus 4: Volvo 9900 (Sleeper)
('A0000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000000', '70000000-0000-0000-0000-000000000002', '80000000-0000-0000-0000-000000000001', '40000000-0000-0000-0000-000000000002', '10000000-0000-0000-0000-000000000001', '20000000-0000-0000-0000-000000000002', '30000000-0000-0000-0000-000000000003', 'BUS-004', 30, 800.0),
-- Bus 5: Mercedes (Luxury)
('A0000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000000', '70000000-0000-0000-0000-000000000001', '80000000-0000-0000-0000-000000000002', '40000000-0000-0000-0000-000000000001', '10000000-0000-0000-0000-000000000003', '20000000-0000-0000-0000-000000000002', '30000000-0000-0000-0000-000000000002', 'BUS-005', 45, 900.0);

-- Bus Amenities Links
INSERT INTO bus_amenities (bus_id, amenity_id) VALUES
('A0000000-0000-0000-0000-000000000001', '50000000-0000-0000-0000-000000000001'), -- Bus 1 has Wifi
('A0000000-0000-0000-0000-000000000002', '50000000-0000-0000-0000-000000000001'), -- Bus 2 has Wifi
('A0000000-0000-0000-0000-000000000002', '50000000-0000-0000-0000-000000000002'), -- Bus 2 has AC
('A0000000-0000-0000-0000-000000000003', '50000000-0000-0000-0000-000000000002'), -- Bus 3 has AC
('A0000000-0000-0000-0000-000000000004', '50000000-0000-0000-0000-000000000002'), -- Bus 4 has AC
('A0000000-0000-0000-0000-000000000004', '50000000-0000-0000-0000-000000000003'); -- Bus 4 has USB

-- Routes
-- Route 1: Central -> Airport
INSERT INTO route (routeid, startlocationid, endlocationid, agencyid) VALUES
('B0000000-0000-0000-0000-000000000001', '60000000-0000-0000-0000-000000000001', '60000000-0000-0000-0000-000000000002', 'test-agency-id');
-- Route 2: Airport -> Central
INSERT INTO route (routeid, startlocationid, endlocationid, agencyid) VALUES
('B0000000-0000-0000-0000-000000000002', '60000000-0000-0000-0000-000000000002', '60000000-0000-0000-0000-000000000001', 'test-agency-id');
-- Route 3: North -> South
INSERT INTO route (routeid, startlocationid, endlocationid, agencyid) VALUES
('B0000000-0000-0000-0000-000000000003', '60000000-0000-0000-0000-000000000003', '60000000-0000-0000-0000-000000000004', 'test-agency-id');

-- Route Stop Points
INSERT INTO route_stop_points (route_id, stop_point) VALUES
('B0000000-0000-0000-0000-000000000001', 'City Center Stop'),
('B0000000-0000-0000-0000-000000000001', 'Highway Exit 5'),
('B0000000-0000-0000-0000-000000000003', 'Midtown Market');

-- Route Prices
-- Price for Route 1 on Bus 1 (Standard)
INSERT INTO route_price (price_id, route_id, bus_id, price_amount, currency, agencyid) VALUES
('C0000000-0000-0000-0000-000000000001', 'B0000000-0000-0000-0000-000000000001', 'A0000000-0000-0000-0000-000000000001', 15.00, 'USD', 'test-agency-id');
-- Price for Route 1 on Bus 2 (Luxury) - Higher price
INSERT INTO route_price (price_id, route_id, bus_id, price_amount, currency, agencyid) VALUES
('C0000000-0000-0000-0000-000000000002', 'B0000000-0000-0000-0000-000000000001', 'A0000000-0000-0000-0000-000000000002', 25.00, 'USD', 'test-agency-id');
-- Price for Route 3 on Bus 3
INSERT INTO route_price (price_id, route_id, bus_id, price_amount, currency, agencyid) VALUES
('C0000000-0000-0000-0000-000000000003', 'B0000000-0000-0000-0000-000000000003', 'A0000000-0000-0000-0000-000000000003', 20.00, 'USD', 'test-agency-id');

-- 4. TRANSACTIONAL DATA

-- Schedules
-- Schedule 1: Tomorrow 10:00 AM (Route 1, Bus 1, Driver 1)
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, agencyid, priceid, driverid) VALUES
('D0000000-0000-0000-0000-000000000001', TO_CHAR(CURRENT_DATE + INTERVAL '1 day', 'YYYY-MM-DD'), '10:00', '12:00', 'B0000000-0000-0000-0000-000000000001', 'A0000000-0000-0000-0000-000000000001', 'test-agency-id', 'C0000000-0000-0000-0000-000000000001', '90000000-0000-0000-0000-000000000001');

-- Schedule 2: Tomorrow 14:00 PM (Route 1, Bus 2, Driver 2)
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, agencyid, priceid, driverid) VALUES
('D0000000-0000-0000-0000-000000000002', TO_CHAR(CURRENT_DATE + INTERVAL '1 day', 'YYYY-MM-DD'), '14:00', '16:00', 'B0000000-0000-0000-0000-000000000001', 'A0000000-0000-0000-0000-000000000002', 'test-agency-id', 'C0000000-0000-0000-0000-000000000002', '90000000-0000-0000-0000-000000000002');

-- Schedule 3: Day after Tomorrow 09:00 AM (Route 3, Bus 3, Driver 3)
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, agencyid, priceid, driverid) VALUES
('D0000000-0000-0000-0000-000000000003', TO_CHAR(CURRENT_DATE + INTERVAL '2 day', 'YYYY-MM-DD'), '09:00', '15:00', 'B0000000-0000-0000-0000-000000000003', 'A0000000-0000-0000-0000-000000000003', 'test-agency-id', 'C0000000-0000-0000-0000-000000000003', '90000000-0000-0000-0000-000000000003');

-- Assignments
-- Assignment 1 for Schedule 1
INSERT INTO assignments (assignment_id, schedule_id, driver_id, agencyid, assignment_date) VALUES
('E0000000-0000-0000-0000-000000000001', 'D0000000-0000-0000-0000-000000000001', '90000000-0000-0000-0000-000000000001', 'test-agency-id', TO_CHAR(CURRENT_DATE + INTERVAL '1 day', 'YYYY-MM-DD'));

-- Assignment 2 for Schedule 2
INSERT INTO assignments (assignment_id, schedule_id, driver_id, agencyid, assignment_date) VALUES
('E0000000-0000-0000-0000-000000000002', 'D0000000-0000-0000-0000-000000000002', '90000000-0000-0000-0000-000000000002', 'test-agency-id', TO_CHAR(CURRENT_DATE + INTERVAL '1 day', 'YYYY-MM-DD'));

-- Assignment 3 for Schedule 3
INSERT INTO assignments (assignment_id, schedule_id, driver_id, agencyid, assignment_date) VALUES
('E0000000-0000-0000-0000-000000000003', 'D0000000-0000-0000-0000-000000000003', '90000000-0000-0000-0000-000000000003', 'test-agency-id', TO_CHAR(CURRENT_DATE + INTERVAL '2 day', 'YYYY-MM-DD'));
