CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE  IF NOT EXISTS buses (
    busid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    busplatenumber VARCHAR(50) NOT NULL UNIQUE,
    buscapacity INT NOT NULL,
    bustype VARCHAR(50) NOT NULL,
    trackingid UUID,
    agencyid VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS fair (
    fairid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fairamount NUMERIC(10,2) NOT NULL,
    agencyid VARCHAR(50) NOT NULL,
    CONSTRAINT uq_fair_amount_agency UNIQUE (agencyid, fairamount)
);

CREATE INDEX idx_fair_agencyid ON fair(agencyid);

-- ========================
-- Location table
-- ========================
CREATE TABLE IF NOT EXISTS location (
    locationid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    locationname VARCHAR(100) NOT NULL,
    agencyid VARCHAR(50) NOT NULL,
    CONSTRAINT uq_location_name_agency UNIQUE (agencyid, locationname)
);

CREATE INDEX idx_location_agencyid ON location(agencyid);

-- ========================
-- Route table
-- ========================

CREATE TABLE  IF NOT EXISTS route (
    routeid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    startlocationid UUID NOT NULL,
    endlocationid UUID NOT NULL,
    agencyid VARCHAR(50) NOT NULL,
    CONSTRAINT uq_route_agency UNIQUE (agencyid, startlocationid, endlocationid),
    CONSTRAINT fk_route_start FOREIGN KEY (startlocationid) REFERENCES Location(locationid),
    CONSTRAINT fk_route_end FOREIGN KEY (endlocationid) REFERENCES Location(locationid)
);

CREATE INDEX idx_route_agencyid ON route(agencyid);

-- ========================
-- Schedule table
-- ========================
CREATE TABLE IF NOT EXISTS schedule (
    scheduleid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date DATE NOT NULL,
    arrivaltime TIME NOT NULL,
    departuretime TIME NOT NULL,
    routeid UUID NOT NULL,
    busid UUID NOT NULL,
    fairid UUID NOT NULL,
    agencyid VARCHAR(50) NOT NULL,
    CONSTRAINT uq_schedule_route_bus_fair UNIQUE (routeid, busid, date, fairid),
    CONSTRAINT fk_schedule_route FOREIGN KEY (routeid) REFERENCES Route(routeid),
    CONSTRAINT fk_schedule_bus FOREIGN KEY (busid) REFERENCES Buses(busid),
    CONSTRAINT fk_schedule_fair FOREIGN KEY (fairid) REFERENCES Fair(fairid)
);

CREATE INDEX idx_schedule_agencyid ON schedule(agencyid);
