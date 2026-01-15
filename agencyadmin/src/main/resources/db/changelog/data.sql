-- ========================
-- Insert Reference Data
-- ========================

-- Bus Makes
INSERT INTO bus_make (bus_make_id, make_name) VALUES
('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Mercedes-Benz'),
('b2c3d4e5-f6a7-8901-bcde-f12345678901', 'Volvo'),
('c3d4e5f6-a7b8-9012-cdef-123456789012', 'Scania'),
('d4e5f6a7-b8c9-0123-defa-234567890123', 'MAN'),
('e5f6a7b8-c9d0-1234-efab-345678901234', 'Iveco')
ON CONFLICT (make_name) DO NOTHING;

-- Bus Manufacturers
INSERT INTO bus_manufacturer (manufacturer_id, manufacturer_name) VALUES
('f6a7b8c9-d0e1-2345-fabc-456789012345', 'Daimler AG'),
('a7b8c9d0-e1f2-3456-abcd-567890123456', 'Volvo Group'),
('b8c9d0e1-f2a3-4567-bcde-678901234567', 'Scania AB'),
('c9d0e1f2-a3b4-5678-cdef-789012345678', 'MAN SE'),
('d0e1f2a3-b4c5-6789-defa-890123456789', 'Iveco S.p.A.')
ON CONFLICT (manufacturer_name) DO NOTHING;

-- Bus Models
INSERT INTO bus_model (bus_model_id, model_name) VALUES
('e1f2a3b4-c5d6-7890-efab-901234567890', 'Sprinter'),
('f2a3b4c5-d6e7-8901-fabc-012345678901', 'Citaro'),
('a3b4c5d6-e7f8-9012-abcd-123456789012', '9700'),
('b4c5d6e7-f8a9-0123-bcde-234567890123', 'Intercity'),
('c5d6e7f8-a9b0-1234-cdef-345678901234', 'Tourismo')
ON CONFLICT DO NOTHING;

-- Bus Types
INSERT INTO bus_type ("busTypeId", "busTypeName") VALUES
('d6e7f8a9-b0c1-2345-defa-456789012345', 'Standard'),
('e7f8a9b0-c1d2-3456-efab-567890123456', 'Luxury'),
('f8a9b0c1-d2e3-4567-fabc-678901234567', 'Mini'),
('a9b0c1d2-e3f4-5678-abcd-789012345678', 'Double Decker'),
('b0c1d2e3-f4a5-6789-bcde-890123456789', 'Sleeper')
ON CONFLICT ("busTypeName") DO NOTHING;

-- Fuel Types
INSERT INTO fuel_type (fuel_type_id, fuel_type_name) VALUES
('c1d2e3f4-a5b6-7890-cdef-901234567890', 'Diesel'),
('d2e3f4a5-b6c7-8901-defa-012345678901', 'Petrol'),
('e3f4a5b6-c7d8-9012-efab-123456789012', 'Electric'),
('f4a5b6c7-d8e9-0123-fabc-234567890123', 'Hybrid'),
('a5b6c7d8-e9f0-1234-abcd-345678901234', 'CNG')
ON CONFLICT (fuel_type_name) DO NOTHING;

-- Transmission Types
INSERT INTO transmission_type (transmission_type_id, type_name) VALUES
('b6c7d8e9-f0a1-2345-bcde-456789012345', 'Manual'),
('c7d8e9f0-a1b2-3456-cdef-567890123456', 'Automatic'),
('d8e9f0a1-b2c3-4567-defa-678901234567', 'Semi-Automatic'),
('e9f0a1b2-c3d4-5678-efab-789012345678', 'CVT')
ON CONFLICT (type_name) DO NOTHING;

-- Drivers
INSERT INTO driver (driver_id, full_name, phone, license_number) VALUES
('f0a1b2c3-d4e5-6789-fabc-890123456789', 'John Smith', '+1234567890', 'DL-001234'),
('a1b2c3d4-e5f6-7890-abcd-901234567890', 'Jane Doe', '+1234567891', 'DL-001235'),
('b2c3d4e5-f6a7-8901-bcde-012345678901', 'Michael Johnson', '+1234567892', 'DL-001236'),
('c3d4e5f6-a7b8-9012-cdef-123456789012', 'Sarah Williams', '+1234567893', 'DL-001237'),
('d4e5f6a7-b8c9-0123-defa-234567890123', 'Robert Brown', '+1234567894', 'DL-001238')
ON CONFLICT (license_number) DO NOTHING;

-- Locations
INSERT INTO location (locationid, locationname, agencyid) VALUES
('e5f6a7b8-c9d0-1234-efab-345678901234', 'Central Station', 'AGENCY001'),
('f6a7b8c9-d0e1-2345-fabc-456789012345', 'Airport Terminal', 'AGENCY001'),
('a7b8c9d0-e1f2-3456-abcd-567890123456', 'North Plaza', 'AGENCY002'),
('b8c9d0e1-f2a3-4567-bcde-678901234567', 'South Beach', 'AGENCY002'),
('c9d0e1f2-a3b4-5678-cdef-789012345678', 'East Market', 'AGENCY003'),
('d0e1f2a3-b4c5-6789-defa-890123456789', 'West Park', 'AGENCY003'),
('e1f2a3b4-c5d6-7890-efab-901234567890', 'Downtown Hub', 'AGENCY001')
ON CONFLICT (locationname) DO NOTHING;

-- Fares
INSERT INTO fair (fairid, fairamount, agencyid) VALUES
('f2a3b4c5-d6e7-8901-fabc-012345678901', 5.50, 'AGENCY001'),
('a3b4c5d6-e7f8-9012-abcd-123456789012', 7.00, 'AGENCY001'),
('b4c5d6e7-f8a9-0123-bcde-234567890123', 4.75, 'AGENCY002'),
('c5d6e7f8-a9b0-1234-cdef-345678901234', 6.25, 'AGENCY002'),
('d6e7f8a9-b0c1-2345-defa-456789012345', 8.50, 'AGENCY003'),
('e7f8a9b0-c1d2-3456-efab-567890123456', 5.00, 'AGENCY003'),
('f8a9b0c1-d2e3-4567-fabc-678901234567', 9.00, 'AGENCY001')
ON CONFLICT (fairamount) DO NOTHING;

-- ========================
-- Insert Main Entity Data
-- ========================

-- Buses (Note: agency_id is UUID in model but using string in data - adjust as needed)
INSERT INTO bus (bus_id, agency_id, bus_make_id, bus_model_id, manufacturer_id, fuel_type_id, transmission_type_id, "busTypeId", registration_number, registration_expiry_date, total_seats, luggage_capacity_kg, tank_capacity_liters, mileage_km) VALUES
('a9b0c1d2-e3f4-5678-abcd-789012345678', '11111111-1111-1111-1111-111111111111', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'e1f2a3b4-c5d6-7890-efab-901234567890', 'f6a7b8c9-d0e1-2345-fabc-456789012345', 'c1d2e3f4-a5b6-7890-cdef-901234567890', 'b6c7d8e9-f0a1-2345-bcde-456789012345', 'd6e7f8a9-b0c1-2345-defa-456789012345', 'ABC-1234', '2025-12-31', 45, 500.00, 200.00, 50000.00),
('b0c1d2e3-f4a5-6789-bcde-890123456789', '11111111-1111-1111-1111-111111111111', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'f2a3b4c5-d6e7-8901-fabc-012345678901', 'a7b8c9d0-e1f2-3456-abcd-567890123456', 'd2e3f4a5-b6c7-8901-defa-012345678901', 'c7d8e9f0-a1b2-3456-cdef-567890123456', 'e7f8a9b0-c1d2-3456-efab-567890123456', 'XYZ-5678', '2026-06-30', 52, 600.00, 250.00, 30000.00),
('c1d2e3f4-a5b6-7890-cdef-901234567890', '22222222-2222-2222-2222-222222222222', 'c3d4e5f6-a7b8-9012-cdef-123456789012', 'a3b4c5d6-e7f8-9012-abcd-123456789012', 'b8c9d0e1-f2a3-4567-bcde-678901234567', 'e3f4a5b6-c7d8-9012-efab-123456789012', 'd8e9f0a1-b2c3-4567-defa-678901234567', 'f8a9b0c1-d2e3-4567-fabc-678901234567', 'DEF-9012', '2025-09-15', 38, 400.00, 150.00, 75000.00),
('d2e3f4a5-b6c7-8901-defa-012345678901', '22222222-2222-2222-2222-222222222222', 'd4e5f6a7-b8c9-0123-defa-234567890123', 'b4c5d6e7-f8a9-0123-bcde-234567890123', 'c9d0e1f2-a3b4-5678-cdef-789012345678', 'f4a5b6c7-d8e9-0123-fabc-234567890123', 'e9f0a1b2-c3d4-5678-efab-789012345678', 'a9b0c1d2-e3f4-5678-abcd-789012345678', 'GHI-3456', '2026-03-20', 45, 500.00, 200.00, 45000.00),
('e3f4a5b6-c7d8-9012-efab-123456789012',
 '33333333-3333-3333-3333-333333333333',
 'e5f6a7b8-c9d0-1234-efab-345678901234',
 'c5d6e7f8-a9b0-1234-cdef-345678901234',
 'd0e1f2a3-b4c5-6789-defa-890123456789',
 'a5b6c7d8-e9f0-1234-abcd-345678901234',
 'c7d8e9f0-a1b2-3456-cdef-567890123456', -- ✅ Automatic
 'b0c1d2e3-f4a5-6789-bcde-890123456789',
 'JKL-7890',
 '2025-11-10',
 60,
 800.00,
 300.00,
 25000.00
)

ON CONFLICT (registration_number) DO NOTHING;

-- Routes
INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY001'
FROM location l1, location l2
WHERE l1.locationname = 'Central Station' AND l2.locationname = 'Airport Terminal'
ON CONFLICT DO NOTHING;

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY001'
FROM location l1, location l2
WHERE l1.locationname = 'Downtown Hub' AND l2.locationname = 'Central Station'
ON CONFLICT DO NOTHING;

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY002'
FROM location l1, location l2
WHERE l1.locationname = 'North Plaza' AND l2.locationname = 'South Beach'
ON CONFLICT DO NOTHING;

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY002'
FROM location l1, location l2
WHERE l1.locationname = 'South Beach' AND l2.locationname = 'North Plaza'
ON CONFLICT DO NOTHING;

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY003'
FROM location l1, location l2
WHERE l1.locationname = 'East Market' AND l2.locationname = 'West Park'
ON CONFLICT DO NOTHING;

-- Route Prices
INSERT INTO route_price (price_id, route_id, bus_id, price_amount, currency, valid_from, valid_to)
SELECT uuid_generate_v4(), r.routeid, b.bus_id, 25.00, 'USD', CURRENT_DATE, CURRENT_DATE + INTERVAL '1 year'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN bus b ON b.registration_number = 'ABC-1234'
WHERE l1.locationname = 'Central Station' AND l2.locationname = 'Airport Terminal'
LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO route_price (price_id, route_id, bus_id, price_amount, currency, valid_from, valid_to)
SELECT uuid_generate_v4(), r.routeid, b.bus_id, 30.00, 'USD', CURRENT_DATE, CURRENT_DATE + INTERVAL '1 year'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN bus b ON b.registration_number = 'XYZ-5678'
WHERE l1.locationname = 'Downtown Hub' AND l2.locationname = 'Central Station'
LIMIT 1
ON CONFLICT DO NOTHING;

-- Schedules
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-01', '09:00:00', '08:00:00',
       r.routeid, b.bus_id, f.fairid, 'AGENCY001'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN bus b ON b.registration_number = 'ABC-1234'
JOIN fair f ON f.fairamount = 5.50 AND f.agencyid = 'AGENCY001'
WHERE l1.locationname = 'Central Station' AND l2.locationname = 'Airport Terminal'
LIMIT 1
ON CONFLICT DO NOTHING;

INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-01', '14:30:00', '13:30:00',
       r.routeid, b.bus_id, f.fairid, 'AGENCY001'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN bus b ON b.registration_number = 'XYZ-5678'
JOIN fair f ON f.fairamount = 7.00 AND f.agencyid = 'AGENCY001'
WHERE l1.locationname = 'Downtown Hub' AND l2.locationname = 'Central Station'
LIMIT 1
ON CONFLICT DO NOTHING;

-- ========================
-- Insert Image Data (Sample S3 References)
-- ========================

-- Bus Images
INSERT INTO bus_image (image_id, bus_id, s3_bucket_name, s3_key, image_url, file_name, content_type, file_size, is_primary, description)
SELECT uuid_generate_v4(), b.bus_id, 'agency-bus-images', 'buses/' || b.bus_id || '/front-view.jpg', 
       'https://agency-bus-images.s3.amazonaws.com/buses/' || b.bus_id || '/front-view.jpg',
       'front-view.jpg', 'image/jpeg', 245760, TRUE, 'Front view of the bus'
FROM bus b
WHERE b.registration_number = 'ABC-1234'
LIMIT 1
ON CONFLICT (s3_key) DO NOTHING;

INSERT INTO bus_image (image_id, bus_id, s3_bucket_name, s3_key, image_url, file_name, content_type, file_size, is_primary, description)
SELECT uuid_generate_v4(), b.bus_id, 'agency-bus-images', 'buses/' || b.bus_id || '/interior.jpg', 
       'https://agency-bus-images.s3.amazonaws.com/buses/' || b.bus_id || '/interior.jpg',
       'interior.jpg', 'image/jpeg', 189440, FALSE, 'Interior view of the bus'
FROM bus b
WHERE b.registration_number = 'ABC-1234'
LIMIT 1
ON CONFLICT (s3_key) DO NOTHING;

-- Driver Images
INSERT INTO driver_image (image_id, driver_id, s3_bucket_name, s3_key, image_url, file_name, content_type, file_size, is_primary, description)
SELECT uuid_generate_v4(), d.driver_id, 'agency-driver-images', 'drivers/' || d.driver_id || '/profile.jpg', 
       'https://agency-driver-images.s3.amazonaws.com/drivers/' || d.driver_id || '/profile.jpg',
       'profile.jpg', 'image/jpeg', 156789, TRUE, 'Driver profile photo'
FROM driver d
WHERE d.license_number = 'DL-001234'
LIMIT 1
ON CONFLICT (s3_key) DO NOTHING;

INSERT INTO driver_image (image_id, driver_id, s3_bucket_name, s3_key, image_url, file_name, content_type, file_size, is_primary, description)
SELECT uuid_generate_v4(), d.driver_id, 'agency-driver-images', 'drivers/' || d.driver_id || '/license.jpg', 
       'https://agency-driver-images.s3.amazonaws.com/drivers/' || d.driver_id || '/license.jpg',
       'license.jpg', 'image/jpeg', 98765, FALSE, 'Driver license copy'
FROM driver d
WHERE d.license_number = 'DL-001234'
LIMIT 1
ON CONFLICT (s3_key) DO NOTHING;
