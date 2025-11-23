-- ========================
-- Buses
-- ========================
INSERT INTO buses (busplatenumber, buscapacity, bustype, trackingid, agencyid)
VALUES 
('ABC-123', 50, 'MiniBus', uuid_generate_v4(), 'AGENCY1'),
('DEF-456', 40, 'Coach', uuid_generate_v4(), 'AGENCY1'),
('GHI-789', 60, 'DoubleDecker', uuid_generate_v4(), 'AGENCY2'),
('JKL-321', 30, 'MiniBus', uuid_generate_v4(), 'AGENCY2'),
('MNO-654', 45, 'Coach', uuid_generate_v4(), 'AGENCY3');

-- ========================
-- Fair
-- ========================
INSERT INTO fair (fairamount, agencyid)
VALUES
(2.50, 'AGENCY1'),
(3.00, 'AGENCY1'),
(2.75, 'AGENCY2'),
(3.50, 'AGENCY2'),
(4.00, 'AGENCY3');

-- ========================
-- Location
-- ========================
INSERT INTO location (locationname, agencyid)
VALUES
('Downtown', 'AGENCY1'),
('Airport', 'AGENCY1'),
('Central Station', 'AGENCY2'),
('University', 'AGENCY2'),
('Mall', 'AGENCY3');

-- ========================
-- Route
-- ========================
-- First, get the UUIDs for the locations to reference
-- Assume we have the following UUIDs:
-- Downtown: uuid1, Airport: uuid2, Central Station: uuid3, University: uuid4, Mall: uuid5
-- Replace these manually or use SELECT to get them
INSERT INTO route (startlocationid, endlocationid, agencyid)
VALUES
((SELECT locationid FROM location WHERE locationname='Downtown'), 
 (SELECT locationid FROM location WHERE locationname='Airport'), 'AGENCY1'),
((SELECT locationid FROM location WHERE locationname='Airport'), 
 (SELECT locationid FROM location WHERE locationname='Downtown'), 'AGENCY1'),
((SELECT locationid FROM location WHERE locationname='Central Station'), 
 (SELECT locationid FROM location WHERE locationname='University'), 'AGENCY2'),
((SELECT locationid FROM location WHERE locationname='University'), 
 (SELECT locationid FROM location WHERE locationname='Central Station'), 'AGENCY2'),
((SELECT locationid FROM location WHERE locationname='Mall'), 
 (SELECT locationid FROM location WHERE locationname='Mall'), 'AGENCY3');

-- ========================
-- Schedule
-- ========================
-- Again, link routeid, busid, fairid using subqueries
INSERT INTO schedule (date, arrivaltime, departuretime, routeid, busid, fairid, agencyid)
VALUES
('2025-11-25', '09:00', '08:00',
 (SELECT routeid FROM route LIMIT 1),
 (SELECT busid FROM buses LIMIT 1),
 (SELECT fairid FROM fair LIMIT 1),
 'AGENCY1'),
('2025-11-25', '10:00', '09:00',
 (SELECT routeid FROM route LIMIT 2 OFFSET 1),
 (SELECT busid FROM buses LIMIT 1 OFFSET 1),
 (SELECT fairid FROM fair LIMIT 1 OFFSET 1),
 'AGENCY1'),
('2025-11-25', '11:00', '10:00',
 (SELECT routeid FROM route LIMIT 1 OFFSET 2),
 (SELECT busid FROM buses LIMIT 1 OFFSET 2),
 (SELECT fairid FROM fair LIMIT 1 OFFSET 2),
 'AGENCY2'),
('2025-11-25', '12:00', '11:00',
 (SELECT routeid FROM route LIMIT 1 OFFSET 3),
 (SELECT busid FROM buses LIMIT 1 OFFSET 3),
 (SELECT fairid FROM fair LIMIT 1 OFFSET 3),
 'AGENCY2'),
('2025-11-25', '13:00', '12:00',
 (SELECT routeid FROM route LIMIT 1 OFFSET 4),
 (SELECT busid FROM buses LIMIT 1 OFFSET 4),
 (SELECT fairid FROM fair LIMIT 1 OFFSET 4),
 'AGENCY3');
