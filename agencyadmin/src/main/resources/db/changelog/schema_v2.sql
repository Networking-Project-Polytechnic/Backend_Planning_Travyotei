-- ========================================================
-- Master Schema Reset - v2.2 (Universal Multi-Agency Support)
-- ========================================================

-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Drop existing tables in correct order
DROP TABLE IF EXISTS assignments CASCADE;
DROP TABLE IF EXISTS bus_image CASCADE;
DROP TABLE IF EXISTS driver_image CASCADE;
DROP TABLE IF EXISTS bus_review CASCADE;
DROP TABLE IF EXISTS bus_amenities CASCADE;
DROP TABLE IF EXISTS bus_transportables CASCADE;
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS route_stop_points CASCADE;
DROP TABLE IF EXISTS route_price CASCADE;
DROP TABLE IF EXISTS route CASCADE;
DROP TABLE IF EXISTS bus CASCADE;
DROP TABLE IF EXISTS location CASCADE;
DROP TABLE IF EXISTS driver CASCADE;
DROP TABLE IF EXISTS fair CASCADE;
DROP TABLE IF EXISTS vehicle_amenity CASCADE;
DROP TABLE IF EXISTS bus_can_transport CASCADE;
DROP TABLE IF EXISTS bus_make CASCADE;
DROP TABLE IF EXISTS bus_manufacturer CASCADE;
DROP TABLE IF EXISTS bus_model CASCADE;
DROP TABLE IF EXISTS bus_type CASCADE;
DROP TABLE IF EXISTS fuel_type CASCADE;
DROP TABLE IF EXISTS transmission_type CASCADE;

-- ========================
-- Reference/Lookup Tables (Scoped to Agency)
-- ========================

CREATE TABLE bus_make (
    bus_make_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    make_name VARCHAR(100) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_bus_make_agency UNIQUE (agencyid, make_name)
);

CREATE TABLE bus_manufacturer (
    manufacturer_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    manufacturer_name VARCHAR(100) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_bus_manufacturer_agency UNIQUE (agencyid, manufacturer_name)
);

CREATE TABLE bus_model (
    bus_model_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    model_name VARCHAR(100) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_bus_model_agency UNIQUE (agencyid, model_name)
);

CREATE TABLE bus_type (
    bus_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bus_type_name VARCHAR(50) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_bus_type_agency UNIQUE (agencyid, bus_type_name)
);

CREATE TABLE fuel_type (
    fuel_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fuel_type_name VARCHAR(50) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_fuel_type_agency UNIQUE (agencyid, fuel_type_name)
);

CREATE TABLE transmission_type (
    transmission_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type_name VARCHAR(50) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_transmission_type_agency UNIQUE (agencyid, type_name)
);

CREATE TABLE vehicle_amenity (
    amenity_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    amenity_name VARCHAR(100) NOT NULL,
    description TEXT,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_vehicle_amenity_agency UNIQUE (agencyid, amenity_name)
);

CREATE TABLE bus_can_transport (
    transport_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    item_name VARCHAR(100) NOT NULL,
    description TEXT,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_bus_can_transport_agency UNIQUE (agencyid, item_name)
);

-- ========================
-- Main Entity Tables
-- ========================

-- Driver Table (Scoped to Agency)
CREATE TABLE driver (
    driver_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    license_number VARCHAR(50) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_driver_license_agency UNIQUE (agencyid, license_number)
);

-- Location Table (Scoped to Agency)
CREATE TABLE location (
    locationid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    locationname VARCHAR(100) NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_location_name_agency UNIQUE (agencyid, locationname)
);

-- Bus Table (Scoped to Agency)
CREATE TABLE bus (
    bus_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    agencyid VARCHAR(100) NOT NULL,
    bus_make_id UUID NOT NULL,
    bus_model_id UUID NOT NULL,
    manufacturer_id UUID NOT NULL,
    fuel_type_id UUID NOT NULL,
    transmission_type_id UUID NOT NULL,
    bus_type_id UUID NOT NULL,
    registration_number VARCHAR(50) NOT NULL,
    registration_expiry_date DATE,
    total_seats INTEGER,
    luggage_capacity_kg NUMERIC(10,2),
    tank_capacity_liters NUMERIC(10,2),
    mileage_km NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_bus_registration_agency UNIQUE (agencyid, registration_number),
    CONSTRAINT fk_bus_make FOREIGN KEY (bus_make_id) REFERENCES bus_make(bus_make_id),
    CONSTRAINT fk_bus_model FOREIGN KEY (bus_model_id) REFERENCES bus_model(bus_model_id),
    CONSTRAINT fk_bus_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES bus_manufacturer(manufacturer_id),
    CONSTRAINT fk_bus_fuel_type FOREIGN KEY (fuel_type_id) REFERENCES fuel_type(fuel_type_id),
    CONSTRAINT fk_bus_transmission_type FOREIGN KEY (transmission_type_id) REFERENCES transmission_type(transmission_type_id),
    CONSTRAINT fk_bus_type FOREIGN KEY (bus_type_id) REFERENCES bus_type(bus_type_id)
);

-- Many-to-Many Links for Bus
CREATE TABLE bus_amenities (
    bus_id UUID NOT NULL,
    amenity_id UUID NOT NULL,
    PRIMARY KEY (bus_id, amenity_id),
    CONSTRAINT fk_bus_amenity_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE,
    CONSTRAINT fk_bus_amenity_item FOREIGN KEY (amenity_id) REFERENCES vehicle_amenity(amenity_id) ON DELETE CASCADE
);

CREATE TABLE bus_transportables (
    bus_id UUID NOT NULL,
    transport_id UUID NOT NULL,
    PRIMARY KEY (bus_id, transport_id),
    CONSTRAINT fk_bus_transport_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE,
    CONSTRAINT fk_bus_transport_item FOREIGN KEY (transport_id) REFERENCES bus_can_transport(transport_id) ON DELETE CASCADE
);

-- Route Table (Scoped to Agency)
CREATE TABLE route (
    routeid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    startlocationid UUID NOT NULL,
    endlocationid UUID NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_route_agency UNIQUE (agencyid, startlocationid, endlocationid),
    CONSTRAINT fk_route_start FOREIGN KEY (startlocationid) REFERENCES location(locationid),
    CONSTRAINT fk_route_end FOREIGN KEY (endlocationid) REFERENCES location(locationid)
);

-- Route Price Table (Scoped to Agency)
CREATE TABLE route_price (
    price_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    route_id UUID NOT NULL,
    bus_id UUID NOT NULL,
    price_amount NUMERIC(10,2) NOT NULL,
    currency VARCHAR(10) DEFAULT 'USD',
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_route_price_agency UNIQUE (agencyid, route_id, bus_id),
    CONSTRAINT fk_route_price_route FOREIGN KEY (route_id) REFERENCES route(routeid),
    CONSTRAINT fk_route_price_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id)
);

-- Schedule Table (The Trip - Scoped to Agency)
CREATE TABLE schedule (
    scheduleid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date VARCHAR(50) NOT NULL,
    arrivaltime VARCHAR(50) NOT NULL,
    departuretime VARCHAR(50) NOT NULL,
    routeid UUID NOT NULL,
    busid UUID NOT NULL,
    priceid UUID NOT NULL, 
    driverid UUID,
    agencyid VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_schedule_trip UNIQUE (agencyid, routeid, busid, priceid, driverid, date, departuretime),
    CONSTRAINT fk_schedule_route FOREIGN KEY (routeid) REFERENCES route(routeid),
    CONSTRAINT fk_schedule_bus FOREIGN KEY (busid) REFERENCES bus(bus_id),
    CONSTRAINT fk_schedule_price FOREIGN KEY (priceid) REFERENCES route_price(price_id),
    CONSTRAINT fk_schedule_driver FOREIGN KEY (driverid) REFERENCES driver(driver_id)
);

-- Assignments Table (Mapping Drivers/Buses to Schedules)
CREATE TABLE assignments (
    assignment_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    schedule_id UUID NOT NULL,
    driver_id UUID NOT NULL,
    bus_id UUID NOT NULL,
    agencyid VARCHAR(100) NOT NULL,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_assign_schedule FOREIGN KEY (schedule_id) REFERENCES schedule(scheduleid) ON DELETE CASCADE,
    CONSTRAINT fk_assign_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id),
    CONSTRAINT fk_assign_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id)
);

-- ========================
-- Meta / Social / Media
-- ========================

CREATE TABLE bus_review (
    review_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bus_id UUID NOT NULL,
    customer_name VARCHAR(100),
    rating INTEGER CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_review_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE
);

CREATE TABLE bus_image (
    image_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bus_id UUID NOT NULL,
    s3_bucket_name VARCHAR(255),
    s3_key VARCHAR(500),
    image_url VARCHAR(1000),
    file_name VARCHAR(255),
    content_type VARCHAR(100),
    file_size BIGINT,
    is_primary BOOLEAN DEFAULT FALSE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    CONSTRAINT fk_bus_image_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE
);

CREATE TABLE driver_image (
    image_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    driver_id UUID NOT NULL,
    s3_bucket_name VARCHAR(255),
    s3_key VARCHAR(500),
    image_url VARCHAR(1000),
    file_name VARCHAR(255),
    content_type VARCHAR(100),
    file_size BIGINT,
    is_primary BOOLEAN DEFAULT FALSE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    CONSTRAINT fk_driver_image_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE
);

-- ========================
-- Indexes for Performance
-- ========================

CREATE INDEX idx_bus_agency ON bus(agencyid);
CREATE INDEX idx_driver_agency ON driver(agencyid);
CREATE INDEX idx_location_agency ON location(agencyid);
CREATE INDEX idx_route_agency ON route(agencyid);
CREATE INDEX idx_schedule_agency ON schedule(agencyid);
CREATE INDEX idx_schedule_date ON schedule(date);
CREATE INDEX idx_assignments_agency ON assignments(agencyid);
