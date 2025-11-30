-- ========================
-- Insert Buses (7 records)
-- ========================
INSERT INTO buses (busid, busplatenumber, buscapacity, bustype, trackingid, agencyid) VALUES
(uuid_generate_v4(), 'ABC-1234', 45, 'Standard', uuid_generate_v4(), 'AGENCY001'),
(uuid_generate_v4(), 'XYZ-5678', 52, 'Luxury', uuid_generate_v4(), 'AGENCY001'),
(uuid_generate_v4(), 'DEF-9012', 38, 'Mini', uuid_generate_v4(), 'AGENCY002'),
(uuid_generate_v4(), 'GHI-3456', 45, 'Standard', uuid_generate_v4(), 'AGENCY002'),
(uuid_generate_v4(), 'JKL-7890', 60, 'Double Decker', uuid_generate_v4(), 'AGENCY003'),
(uuid_generate_v4(), 'MNO-2345', 40, 'Standard', uuid_generate_v4(), 'AGENCY003'),
(uuid_generate_v4(), 'PQR-6789', 35, 'Mini', uuid_generate_v4(), 'AGENCY001');

-- ========================
-- Insert Fair (7 records)
-- ========================
INSERT INTO fair (fairid, fairamount, agencyid) VALUES
(uuid_generate_v4(), 5.50, 'AGENCY001'),
(uuid_generate_v4(), 7.00, 'AGENCY001'),
(uuid_generate_v4(), 4.75, 'AGENCY002'),
(uuid_generate_v4(), 6.25, 'AGENCY002'),
(uuid_generate_v4(), 8.50, 'AGENCY003'),
(uuid_generate_v4(), 5.00, 'AGENCY003'),
(uuid_generate_v4(), 9.00, 'AGENCY001');

-- ========================
-- Insert Location (7 records)
-- ========================
INSERT INTO location (locationid, locationname, agencyid) VALUES
(uuid_generate_v4(), 'Central Station', 'AGENCY001'),
(uuid_generate_v4(), 'Airport Terminal', 'AGENCY001'),
(uuid_generate_v4(), 'North Plaza', 'AGENCY002'),
(uuid_generate_v4(), 'South Beach', 'AGENCY002'),
(uuid_generate_v4(), 'East Market', 'AGENCY003'),
(uuid_generate_v4(), 'West Park', 'AGENCY003'),
(uuid_generate_v4(), 'Downtown Hub', 'AGENCY001');

-- ========================
-- Insert Route (7 records)
-- ========================
INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY001'
FROM location l1, location l2
WHERE l1.locationname = 'Central Station' AND l2.locationname = 'Airport Terminal';

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY001'
FROM location l1, location l2
WHERE l1.locationname = 'Downtown Hub' AND l2.locationname = 'Central Station';

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY002'
FROM location l1, location l2
WHERE l1.locationname = 'North Plaza' AND l2.locationname = 'South Beach';

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY002'
FROM location l1, location l2
WHERE l1.locationname = 'South Beach' AND l2.locationname = 'North Plaza';

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY003'
FROM location l1, location l2
WHERE l1.locationname = 'East Market' AND l2.locationname = 'West Park';

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY003'
FROM location l1, location l2
WHERE l1.locationname = 'West Park' AND l2.locationname = 'East Market';

INSERT INTO route (routeid, startlocationid, endlocationid, agencyid)
SELECT uuid_generate_v4(), l1.locationid, l2.locationid, 'AGENCY001'
FROM location l1, location l2
WHERE l1.locationname = 'Airport Terminal' AND l2.locationname = 'Downtown Hub';

-- ========================
-- Insert Schedule (7 records)
-- ========================
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-01', '09:00:00', '08:00:00',
       r.routeid, b.busid, f.fairid, 'AGENCY001'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN buses b ON b.busplatenumber = 'ABC-1234'
JOIN fair f ON f.fairamount = 5.50 AND f.agencyid = 'AGENCY001'
WHERE l1.locationname = 'Central Station' AND l2.locationname = 'Airport Terminal';

INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-01', '14:30:00', '13:30:00',
       r.routeid, b.busid, f.fairid, 'AGENCY001'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN buses b ON b.busplatenumber = 'XYZ-5678'
JOIN fair f ON f.fairamount = 7.00 AND f.agencyid = 'AGENCY001'
WHERE l1.locationname = 'Downtown Hub' AND l2.locationname = 'Central Station';

-- Schedule 3
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-02', '11:00:00', '10:00:00',
       r.routeid, b.busid, f.fairid, 'AGENCY002'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN buses b ON b.busplatenumber = 'DEF-9012'
JOIN fair f ON f.fairamount = 4.75 AND f.agencyid = 'AGENCY002'
WHERE l1.locationname = 'North Plaza' AND l2.locationname = 'South Beach';

-- Schedule 4
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-02', '16:45:00', '15:45:00',
       r.routeid, b.busid, f.fairid, 'AGENCY002'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN buses b ON b.busplatenumber = 'GHI-3456'
JOIN fair f ON f.fairamount = 6.25 AND f.agencyid = 'AGENCY002'
WHERE l1.locationname = 'South Beach' AND l2.locationname = 'North Plaza';

-- Schedule 5
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-03', '12:30:00', '11:00:00',
       r.routeid, b.busid, f.fairid, 'AGENCY003'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN buses b ON b.busplatenumber = 'JKL-7890'
JOIN fair f ON f.fairamount = 8.50 AND f.agencyid = 'AGENCY003'
WHERE l1.locationname = 'East Market' AND l2.locationname = 'West Park';

-- Schedule 6
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-03', '18:00:00', '17:00:00',
       r.routeid, b.busid, f.fairid, 'AGENCY003'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN buses b ON b.busplatenumber = 'MNO-2345'
JOIN fair f ON f.fairamount = 5.00 AND f.agencyid = 'AGENCY003'
WHERE l1.locationname = 'West Park' AND l2.locationname = 'East Market';

-- Schedule 7
INSERT INTO schedule (scheduleid, date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
SELECT uuid_generate_v4(), '2024-12-04', '10:30:00', '09:00:00',
       r.routeid, b.busid, f.fairid, 'AGENCY001'
FROM route r
JOIN location l1 ON r.startlocationid = l1.locationid
JOIN location l2 ON r.endlocationid = l2.locationid
JOIN buses b ON b.busplatenumber = 'PQR-6789'
JOIN fair f ON f.fairamount = 9.00 AND f.agencyid = 'AGENCY001'
WHERE l1.locationname = 'Airport Terminal' AND l2.locationname = 'Downtown Hub';
