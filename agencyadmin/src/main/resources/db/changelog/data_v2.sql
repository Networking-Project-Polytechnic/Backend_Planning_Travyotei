-- ========================
-- Master Data Seed - v2.3
-- Specific Agency: 5baa95c3-40c4-4e7d-be4b-f4aad384b904
-- ========================

-- Reference Data for Agency
INSERT INTO bus_make (bus_make_id, make_name, agencyid) VALUES
(uuid_generate_v4(), 'Mercedes-Benz', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Volvo', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Toyota', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Scania', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Yutong', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

INSERT INTO bus_manufacturer (manufacturer_id, manufacturer_name, agencyid) VALUES
(uuid_generate_v4(), 'Daimler Truck', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Volvo Buses', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Toyota Motor Corp', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Yutong Bus Co.', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

INSERT INTO bus_model (bus_model_id, model_name, agencyid) VALUES
(uuid_generate_v4(), 'Sprinter 515', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Citaro G', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Coaster', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Hiace', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'ZK6122H9', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

INSERT INTO bus_type (bus_type_id, bus_type_name, agencyid) VALUES
(uuid_generate_v4(), 'Coaster (30 Seats)', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Gros Porteur (70 Seats)', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'VIP Luxury', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Mini-Bus (18 Seats)', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

INSERT INTO fuel_type (fuel_type_id, fuel_type_name, agencyid) VALUES
(uuid_generate_v4(), 'Gasoil', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Essence', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

INSERT INTO transmission_type (transmission_type_id, type_name, agencyid) VALUES
(uuid_generate_v4(), 'Manuelle', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Automatique', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

INSERT INTO vehicle_amenity (amenity_id, amenity_name, description, agencyid) VALUES
(uuid_generate_v4(), 'Climatisation', 'AC for the entire cabin', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Wi-Fi Gratuit', 'High speed internet', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Prises USB', 'Charging ports at every seat', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Toilettes', 'On-board restroom', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Sièges Inclinables', 'Extra legroom and comfort', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

-- Drivers for the Agency
INSERT INTO driver (driver_id, full_name, phone, license_number, agencyid) VALUES
(uuid_generate_v4(), 'Tanyi Emmanuel', '677221144', 'NW-229-X', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Foning Serge', '699334455', 'LT-102-Y', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Abena Francis', '655447788', 'CE-883-Z', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Kamga Paul', '622115566', 'OU-554-A', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Moussa Bakari', '644112233', 'AD-332-B', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

-- Locations for the Agency
INSERT INTO location (locationid, locationname, agencyid) VALUES
(uuid_generate_v4(), 'Yaounde (Mvan)', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Douala (Akwa)', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Bafoussam', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Bamenda', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Ngaoundere', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Garoua', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Maroua', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Kribi', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Limbe', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'),
(uuid_generate_v4(), 'Buea', '5baa95c3-40c4-4e7d-be4b-f4aad384b904');

-- Buses for the Agency
INSERT INTO bus (bus_id, agencyid, bus_make_id, bus_model_id, manufacturer_id, fuel_type_id, transmission_type_id, bus_type_id, registration_number, total_seats)
SELECT uuid_generate_v4(), '5baa95c3-40c4-4e7d-be4b-f4aad384b904', 
       (SELECT bus_make_id FROM bus_make WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' LIMIT 1),
       (SELECT bus_model_id FROM bus_model WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' LIMIT 1),
       (SELECT manufacturer_id FROM bus_manufacturer WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' LIMIT 1),
       (SELECT fuel_type_id FROM fuel_type WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' LIMIT 1),
       (SELECT transmission_type_id FROM transmission_type WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' LIMIT 1),
       (SELECT bus_type_id FROM bus_type WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' LIMIT 1),
       'CE-' || (100 + i) || '-X', 
       CASE WHEN i % 2 = 0 THEN 30 ELSE 70 END
FROM generate_series(1, 10) AS i;

-- Routes for the Agency
INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, '5baa95c3-40c4-4e7d-be4b-f4aad384b904'
FROM location l1, location l2
WHERE l1.agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' 
  AND l2.agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904'
  AND l1.locationname != l2.locationname
LIMIT 20;

-- Route Prices
INSERT INTO route_price (price_id, route_id, bus_id, price_amount, currency, agencyid)
SELECT uuid_generate_v4(), r.routeid, b.bus_id, 
       CASE WHEN (SELECT count(*) FROM route_price WHERE route_id = r.routeid) = 0 THEN 3000 ELSE 6000 END,
       'XAF', '5baa95c3-40c4-4e7d-be4b-f4aad384b904'
FROM route r
CROSS JOIN LATERAL (SELECT bus_id FROM bus WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' LIMIT 2) b
WHERE r.agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904';

-- Schedules (Trips)
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, priceid, driverid, agencyid)
SELECT uuid_generate_v4(), 
       current_date::text,
       LPAD((10 + i)::text, 2, '0') || ':00', 
       LPAD((5 + i)::text, 2, '0') || ':00',
       p.route_id, p.bus_id, p.price_id,
       (SELECT driver_id FROM driver WHERE agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904' OFFSET (i % 5) LIMIT 1),
       '5baa95c3-40c4-4e7d-be4b-f4aad384b904'
FROM route_price p
CROSS JOIN generate_series(1, 4) AS i
WHERE p.agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904'
LIMIT 50;

-- Simple data for another agency to prove isolation
INSERT INTO location (locationid, locationname, agencyid) VALUES
(uuid_generate_v4(), 'Bamenda (Up Station)', 'other-agency-id');

-- Seed Assignments for the generated schedules
INSERT INTO assignments (assignment_id, schedule_id, driver_id, bus_id, agencyid, assignment_date)
SELECT uuid_generate_v4(), 
       s.scheduleid, 
       s.driverid, 
       s.busid, 
       s.agencyid, 
       s.date
FROM schedule s
WHERE s.agencyid = '5baa95c3-40c4-4e7d-be4b-f4aad384b904'
LIMIT 10;
