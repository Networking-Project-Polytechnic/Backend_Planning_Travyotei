-- Reset Database: Truncate all tables
TRUNCATE TABLE 
    bus_image, 
    driver_image, 
    bus_review, 
    bus_amenities, 
    bus_transportables, 
    schedule, 
    route_stop_points,
    route_price, 
    route, 
    bus, 
    location, 
    driver, 
    vehicle_amenity, 
    bus_can_transport, 
    bus_make, 
    bus_manufacturer, 
    bus_model, 
    bus_type, 
    fuel_type, 
    transmission_type 
CASCADE;

-- ========================
-- Repopulate Reference Data
-- ========================

-- Bus Makes
INSERT INTO bus_make (bus_make_id, make_name) VALUES
('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Mercedes-Benz'),
('b2c3d4e5-f6a7-8901-bcde-f12345678901', 'Volvo'),
('c3d4e5f6-a7b8-9012-cdef-123456789012', 'Scania'),
('d4e5f6a7-b8c9-0123-defa-234567890123', 'MAN'),
('e5f6a7b8-c9d0-1234-efab-345678901234', 'Iveco');

-- Bus Manufacturers
INSERT INTO bus_manufacturer (manufacturer_id, manufacturer_name) VALUES
('f6a7b8c9-d0e1-2345-fabc-456789012345', 'Daimler AG'),
('a7b8c9d0-e1f2-3456-abcd-567890123456', 'Volvo Group'),
('b8c9d0e1-f2a3-4567-bcde-678901234567', 'Scania AB'),
('c9d0e1f2-a3b4-5678-cdef-789012345678', 'MAN SE'),
('d0e1f2a3-b4c5-6789-defa-890123456789', 'Iveco S.p.A.');

-- Bus Models
INSERT INTO bus_model (bus_model_id, model_name) VALUES
('e1f2a3b4-c5d6-7890-efab-901234567890', 'Sprinter'),
('f2a3b4c5-d6e7-8901-fabc-012345678901', 'Citaro'),
('a3b4c5d6-e7f8-9012-abcd-123456789012', '9700'),
('b4c5d6e7-f8a9-0123-bcde-234567890123', 'Intercity'),
('c5d6e7f8-a9b0-1234-cdef-345678901234', 'Tourismo');

-- Bus Types
INSERT INTO bus_type (bus_type_id, bus_type_name) VALUES
('d6e7f8a9-b0c1-2345-defa-456789012345', 'Standard'),
('e7f8a9b0-c1d2-3456-efab-567890123456', 'Luxury'),
('f8a9b0c1-d2e3-4567-fabc-678901234567', 'Mini'),
('a9b0c1d2-e3f4-5678-abcd-789012345678', 'Double Decker'),
('b0c1d2e3-f4a5-6789-bcde-890123456789', 'Sleeper');

-- Fuel Types
INSERT INTO fuel_type (fuel_type_id, fuel_type_name) VALUES
('c1d2e3f4-a5b6-7890-cdef-901234567890', 'Diesel'),
('d2e3f4a5-b6c7-8901-defa-012345678901', 'Petrol'),
('e3f4a5b6-c7d8-9012-efab-123456789012', 'Electric'),
('f4a5b6c7-d8e9-0123-fabc-234567890123', 'Hybrid'),
('a5b6c7d8-e9f0-1234-abcd-345678901234', 'CNG');

-- Transmission Types
INSERT INTO transmission_type (transmission_type_id, type_name) VALUES
('b6c7d8e9-f0a1-2345-bcde-456789012345', 'Manual'),
('c7d8e9f0-a1b2-3456-cdef-567890123456', 'Automatic'),
('d8e9f0a1-b2c3-4567-defa-678901234567', 'Semi-Automatic'),
('e9f0a1b2-c3d4-5678-efab-789012345678', 'CVT');

-- Vehicle Amenities
INSERT INTO vehicle_amenity (amenity_id, amenity_name, description) VALUES
('f1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Wi-Fi', 'High-speed wireless internet'),
('f2c3d4e5-f6a7-8901-bcde-f12345678901', 'Air Conditioning', 'Climate control for passenger comfort'),
('f3d4e5f6-a7b8-9012-cdef-123456789012', 'USB Charging', 'Individual USB ports at each seat'),
('f4e5f6a7-b8c9-0123-defa-234567890123', 'Toilet', 'On-board restroom facility'),
('f5f6a7b8-c9d0-1234-efab-345678901234', 'Reclining Seats', 'Extra comfort for long journeys');

-- Bus Can Transport
INSERT INTO bus_can_transport (transport_id, item_name, description) VALUES
('01b2c3d4-e5f6-7890-abcd-ef1234567890', 'Bicycles', 'Special racks for carrying bikes'),
('02c3d4e5-f6a7-8901-bcde-f12345678901', 'Large Luggage', 'Spacious under-bus storage'),
('03d4e5f6-a7b8-9012-cdef-123456789012', 'Pets', 'Designated area for pet carriers'),
('04e5f6a7-b8c9-0123-defa-234567890123', 'Wheelchairs', 'Ramp and secure space for wheelchairs');

-- Drivers (Fixed with agencyid)
INSERT INTO driver (driver_id, full_name, phone, license_number, agencyid) VALUES
('f0a1b2c3-d4e5-6789-fabc-890123456789', 'John Smith', '+1234567890', 'DL-001234', '11111111-1111-1111-1111-111111111111'),
('a1b2c3d4-e5f6-7890-abcd-901234567890', 'Jane Doe', '+1234567891', 'DL-001235', '11111111-1111-1111-1111-111111111111'),
('b2c3d4e5-f6a7-8901-bcde-012345678901', 'Michael Johnson', '+1234567892', 'DL-001236', '22222222-2222-2222-2222-222222222222'),
('c3d4e5f6-a7b8-9012-cdef-123456789012', 'Sarah Williams', '+1234567893', 'DL-001237', '22222222-2222-2222-2222-222222222222'),
('d4e5f6a7-b8c9-0123-defa-234567890123', 'Robert Brown', '+1234567894', 'DL-001238', '33333333-3333-3333-3333-333333333333');

-- Locations
INSERT INTO location (locationid, locationname, agencyid) VALUES
('e5f6a7b8-c9d0-1234-efab-345678901234', 'Central Station', '11111111-1111-1111-1111-111111111111'),
('f6a7b8c9-d0e1-2345-fabc-456789012345', 'Airport Terminal', '11111111-1111-1111-1111-111111111111'),
('a7b8c9d0-e1f2-3456-abcd-567890123456', 'North Plaza', '22222222-2222-2222-2222-222222222222'),
('b8c9d0e1-f2a3-4567-bcde-678901234567', 'South Beach', '22222222-2222-2222-2222-222222222222'),
('c9d0e1f2-a3b4-5678-cdef-789012345678', 'East Market', '33333333-3333-3333-3333-333333333333'),
('d0e1f2a3-b4c5-6789-defa-890123456789', 'West Park', '33333333-3333-3333-3333-333333333333'),
('e1f2a3b4-c5d6-7890-efab-901234567890', 'Downtown Hub', '11111111-1111-1111-1111-111111111111');

-- Buses
INSERT INTO bus (bus_id, agency_id, bus_make_id, bus_model_id, manufacturer_id, fuel_type_id, transmission_type_id, bus_type_id, registration_number, registration_expiry_date, total_seats, luggage_capacity_kg, tank_capacity_liters, mileage_km) VALUES
('a9b0c1d2-e3f4-5678-abcd-789012345678', '11111111-1111-1111-1111-111111111111', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'e1f2a3b4-c5d6-7890-efab-901234567890', 'f6a7b8c9-d0e1-2345-fabc-456789012345', 'c1d2e3f4-a5b6-7890-cdef-901234567890', 'b6c7d8e9-f0a1-2345-bcde-456789012345', 'd6e7f8a9-b0c1-2345-defa-456789012345', 'ABC-1234', '2025-12-31', 45, 500.00, 200.00, 50000.00),
('b0c1d2e3-f4a5-6789-bcde-890123456789', '11111111-1111-1111-1111-111111111111', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'f2a3b4c5-d6e7-8901-fabc-012345678901', 'a7b8c9d0-e1f2-3456-abcd-567890123456', 'd2e3f4a5-b6c7-8901-defa-012345678901', 'c7d8e9f0-a1b2-3456-cdef-567890123456', 'e7f8a9b0-c1d2-3456-efab-567890123456', 'XYZ-5678', '2026-06-30', 52, 600.00, 250.00, 30000.00),
('c1d2e3f4-a5b6-7890-cdef-901234567890', '22222222-2222-2222-2222-222222222222', 'c3d4e5f6-a7b8-9012-cdef-123456789012', 'a3b4c5d6-e7f8-9012-abcd-123456789012', 'b8c9d0e1-f2a3-4567-bcde-678901234567', 'e3f4a5b6-c7d8-9012-efab-123456789012', 'd8e9f0a1-b2c3-4567-defa-678901234567', 'f8a9b0c1-d2e3-4567-fabc-678901234567', 'DEF-9012', '2025-09-15', 38, 400.00, 150.00, 75000.00),
('d2e3f4a5-b6c7-8901-defa-012345678901', '22222222-2222-2222-2222-222222222222', 'd4e5f6a7-b8c9-0123-defa-234567890123', 'b4c5d6e7-f8a9-0123-bcde-234567890123', 'c9d0e1f2-a3b4-5678-cdef-789012345678', 'f4a5b6c7-d8e9-0123-fabc-234567890123', 'e9f0a1b2-c3d4-5678-efab-789012345678', 'a9b0c1d2-e3f4-5678-abcd-789012345678', 'GHI-3456', '2026-03-20', 45, 500.00, 200.00, 45000.00),
('e3f4a5b6-c7d8-9012-efab-123456789012', '33333333-3333-3333-3333-333333333333', 'e5f6a7b8-c9d0-1234-efab-345678901234', 'c5d6e7f8-a9b0-1234-cdef-345678901234', 'd0e1f2a3-b4c5-6789-defa-890123456789', 'a5b6c7d8-e9f0-1234-abcd-345678901234', 'c7d8e9f0-a1b2-3456-cdef-567890123456', 'b0c1d2e3-f4a5-6789-bcde-890123456789', 'JKL-7890', '2025-11-10', 60, 800.00, 300.00, 25000.00);

-- Bus Amenities Links
INSERT INTO bus_amenities (bus_id, amenity_id) VALUES
('a9b0c1d2-e3f4-5678-abcd-789012345678', 'f1b2c3d4-e5f6-7890-abcd-ef1234567890'), -- Wi-Fi
('a9b0c1d2-e3f4-5678-abcd-789012345678', 'f2c3d4e5-f6a7-8901-bcde-f12345678901'), -- A/C
('b0c1d2e3-f4a5-6789-bcde-890123456789', 'f1b2c3d4-e5f6-7890-abcd-ef1234567890'), -- Wi-Fi
('b0c1d2e3-f4a5-6789-bcde-890123456789', 'f3d4e5f6-a7b8-9012-cdef-123456789012'); -- USB

-- Bus Transportables Links
INSERT INTO bus_transportables (bus_id, transport_id) VALUES
('a9b0c1d2-e3f4-5678-abcd-789012345678', '01b2c3d4-e5f6-7890-abcd-ef1234567890'), -- Bicycles
('a9b0c1d2-e3f4-5678-abcd-789012345678', '02c3d4e5-f6a7-8901-bcde-f12345678901'), -- Large Luggage
('b0c1d2e3-f4a5-6789-bcde-890123456789', '02c3d4e5-f6a7-8901-bcde-f12345678901'); -- Large Luggage

-- Routes
INSERT INTO route (routeid, startlocationid, endlocationid, agencyid) VALUES
('71b2c3d4-e5f6-7890-abcd-ef1234567890', 'e5f6a7b8-c9d0-1234-efab-345678901234', 'f6a7b8c9-d0e1-2345-fabc-456789012345', '11111111-1111-1111-1111-111111111111'), -- Central to Airport
('72c3d4e5-f6a7-8901-bcde-f12345678901', 'e1f2a3b4-c5d6-7890-efab-901234567890', 'e5f6a7b8-c9d0-1234-efab-345678901234', '11111111-1111-1111-1111-111111111111'); -- Downtown to Central

-- Route Stop Points
INSERT INTO route_stop_points (route_id, stop_point) VALUES
('71b2c3d4-e5f6-7890-abcd-ef1234567890', 'Midway Bus Stop'),
('71b2c3d4-e5f6-7890-abcd-ef1234567890', 'Terminal 1 Junction'),
('72c3d4e5-f6a7-8901-bcde-f12345678901', 'City Library');

-- Route Prices (Refactored: no valid_from/to)
INSERT INTO route_price (price_id, route_id, bus_id, price_amount, currency) VALUES
('81b2c3d4-e5f6-7890-abcd-ef1234567890', '71b2c3d4-e5f6-7890-abcd-ef1234567890', 'a9b0c1d2-e3f4-5678-abcd-789012345678', 25.00, 'USD'),
('82c3d4e5-f6a7-8901-bcde-f12345678901', '72c3d4e5-f6a7-8901-bcde-f12345678901', 'b0c1d2e3-f4a5-6789-bcde-890123456789', 30.00, 'USD');

-- Schedules (Refactored: priceid instead of fairid, added driverid)
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, priceid, agencyid, driverid) VALUES
('91b2c3d4-e5f6-7890-abcd-ef1234567890', '2024-12-01', '09:00:00', '08:00:00', '71b2c3d4-e5f6-7890-abcd-ef1234567890', 'a9b0c1d2-e3f4-5678-abcd-789012345678', '81b2c3d4-e5f6-7890-abcd-ef1234567890', '11111111-1111-1111-1111-111111111111', 'f0a1b2c3-d4e5-6789-fabc-890123456789'),
('92c3d4e5-f6a7-8901-bcde-f12345678901', '2024-12-01', '14:30:00', '13:30:00', '72c3d4e5-f6a7-8901-bcde-f12345678901', 'b0c1d2e3-f4a5-6789-bcde-890123456789', '82c3d4e5-f6a7-8901-bcde-f12345678901', '11111111-1111-1111-1111-111111111111', 'a1b2c3d4-e5f6-7890-abcd-901234567890');

-- Bus Reviews
INSERT INTO bus_review (review_id, bus_id, customer_name, rating, comment) VALUES
('51b2c3d4-e5f6-7890-abcd-ef1234567890', 'a9b0c1d2-e3f4-5678-abcd-789012345678', 'Alice', 5, 'Very comfortable and on time!'),
('52c3d4e5-f6a7-8901-bcde-f12345678901', 'a9b0c1d2-e3f4-5678-abcd-789012345678', 'Bob', 4, 'Good service, Wi-Fi was a bit slow.'),
('53d4e5f6-a7b8-9012-cdef-123456789012', 'b0c1d2e3-f4a5-6789-bcde-890123456789', 'Charlie', 5, 'Luxury experience, totally worth it.');

-- Bus Images
INSERT INTO bus_image (image_id, bus_id, s3_bucket_name, s3_key, image_url, file_name, content_type, file_size, is_primary, description) VALUES
('41b2c3d4-e5f6-7890-abcd-ef1234567890', 'a9b0c1d2-e3f4-5678-abcd-789012345678', 'agency-bus-images', 'buses/front-view-1.jpg', 'https://example.com/front1.jpg', 'front.jpg', 'image/jpeg', 245760, TRUE, 'Front view'),
('42c3d4e5-f6a7-8901-bcde-f12345678901', 'b0c1d2e3-f4a5-6789-bcde-890123456789', 'agency-bus-images', 'buses/front-view-2.jpg', 'https://example.com/front2.jpg', 'front.jpg', 'image/jpeg', 245760, TRUE, 'Front view');

-- Driver Images
INSERT INTO driver_image (image_id, driver_id, s3_bucket_name, s3_key, image_url, file_name, content_type, file_size, is_primary, description) VALUES
('d1b2c3d4-e5f6-7890-abcd-ef1234567890', 'f0a1b2c3-d4e5-6789-fabc-890123456789', 'agency-driver-images', 'drivers/john.jpg', 'https://example.com/john.jpg', 'john.jpg', 'image/jpeg', 156789, TRUE, 'Profile photo');
