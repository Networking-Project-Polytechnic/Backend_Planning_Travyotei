-- ========================
-- Enable UUID extension
-- ========================
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- ========================
-- Reference/Lookup Tables (No Dependencies)
-- ========================

-- Bus Make Table
CREATE TABLE IF NOT EXISTS bus_make (
    bus_make_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    make_name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bus_make_name ON bus_make(make_name);

-- Bus Manufacturer Table
CREATE TABLE IF NOT EXISTS bus_manufacturer (
    manufacturer_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    manufacturer_name VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bus_manufacturer_name ON bus_manufacturer(manufacturer_name);

-- Bus Model Table
CREATE TABLE IF NOT EXISTS bus_model (
    bus_model_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    model_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bus_model_name ON bus_model(model_name);

-- Bus Type Table
CREATE TABLE IF NOT EXISTS bus_type (
    "busTypeId" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    "busTypeName" VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_bus_type_name ON bus_type("busTypeName");

-- Fuel Type Table
CREATE TABLE IF NOT EXISTS fuel_type (
    fuel_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fuel_type_name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_fuel_type_name ON fuel_type(fuel_type_name);

-- Transmission Type Table
CREATE TABLE IF NOT EXISTS transmission_type (
    transmission_type_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type_name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_transmission_type_name ON transmission_type(type_name);

-- Driver Table
CREATE TABLE IF NOT EXISTS driver (
    driver_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    license_number VARCHAR(50) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_driver_license ON driver(license_number);
CREATE INDEX idx_driver_full_name ON driver(full_name);

-- Location Table
CREATE TABLE IF NOT EXISTS location (
    locationid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    locationname VARCHAR(100) NOT NULL UNIQUE,
    agencyid VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_location_name_agency UNIQUE (agencyid, locationname)
);

CREATE INDEX idx_location_agencyid ON location(agencyid);
CREATE INDEX idx_location_name ON location(locationname);

-- Fair (Fare) Table
CREATE TABLE IF NOT EXISTS fair (
    fairid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    fairamount NUMERIC(10,2) NOT NULL UNIQUE,
    agencyid VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_fair_agencyid ON fair(agencyid);
CREATE INDEX idx_fair_amount ON fair(fairamount);

-- ========================
-- Main Entity Tables (With Dependencies)
-- ========================

-- Bus Table
CREATE TABLE IF NOT EXISTS bus (
    bus_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    agency_id UUID NOT NULL,
    bus_make_id UUID NOT NULL,
    bus_model_id UUID NOT NULL,
    manufacturer_id UUID NOT NULL,
    fuel_type_id UUID NOT NULL,
    transmission_type_id UUID NOT NULL,
    "busTypeId" UUID NOT NULL,
    registration_number VARCHAR(50) NOT NULL UNIQUE,
    registration_expiry_date DATE,
    total_seats INTEGER,
    luggage_capacity_kg NUMERIC(10,2),
    tank_capacity_liters NUMERIC(10,2),
    mileage_km NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bus_make FOREIGN KEY (bus_make_id) REFERENCES bus_make(bus_make_id),
    CONSTRAINT fk_bus_model FOREIGN KEY (bus_model_id) REFERENCES bus_model(bus_model_id),
    CONSTRAINT fk_bus_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES bus_manufacturer(manufacturer_id),
    CONSTRAINT fk_bus_fuel_type FOREIGN KEY (fuel_type_id) REFERENCES fuel_type(fuel_type_id),
    CONSTRAINT fk_bus_transmission_type FOREIGN KEY (transmission_type_id) REFERENCES transmission_type(transmission_type_id),
    CONSTRAINT fk_bus_type FOREIGN KEY ("busTypeId") REFERENCES bus_type("busTypeId")
);

CREATE INDEX idx_bus_agency_id ON bus(agency_id);
CREATE INDEX idx_bus_registration_number ON bus(registration_number);
CREATE INDEX idx_bus_make_id ON bus(bus_make_id);
CREATE INDEX idx_bus_model_id ON bus(bus_model_id);

-- Route Table
CREATE TABLE IF NOT EXISTS route (
    routeid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    startlocationid UUID NOT NULL,
    endlocationid UUID NOT NULL,
    agencyid VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_route_agency UNIQUE (agencyid, startlocationid, endlocationid),
    CONSTRAINT fk_route_start FOREIGN KEY (startlocationid) REFERENCES location(locationid),
    CONSTRAINT fk_route_end FOREIGN KEY (endlocationid) REFERENCES location(locationid)
);

CREATE INDEX idx_route_agencyid ON route(agencyid);
CREATE INDEX idx_route_start_location ON route(startlocationid);
CREATE INDEX idx_route_end_location ON route(endlocationid);

-- Route Price Table
CREATE TABLE IF NOT EXISTS route_price (
    price_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    route_id UUID NOT NULL,
    bus_id UUID NOT NULL,
    price_amount NUMERIC(10,2) NOT NULL,
    currency VARCHAR(10) DEFAULT 'USD',
    valid_from DATE,
    valid_to DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_route_price_route FOREIGN KEY (route_id) REFERENCES route(routeid),
    CONSTRAINT fk_route_price_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id)
);

CREATE INDEX idx_route_price_route_id ON route_price(route_id);
CREATE INDEX idx_route_price_bus_id ON route_price(bus_id);
CREATE INDEX idx_route_price_valid_dates ON route_price(valid_from, valid_to);

-- Schedule Table
CREATE TABLE IF NOT EXISTS schedule (
    scheduleid UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    date VARCHAR(50) NOT NULL,
    arrivaltime VARCHAR(50) NOT NULL,
    departuretime VARCHAR(50) NOT NULL,
    routeid UUID NOT NULL,
    busid UUID NOT NULL,
    agencyid VARCHAR(50) NOT NULL,
    fairid UUID NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_schedule_route_bus_fair UNIQUE (routeid, busid, date, fairid),
    CONSTRAINT fk_schedule_route FOREIGN KEY (routeid) REFERENCES route(routeid),
    CONSTRAINT fk_schedule_bus FOREIGN KEY (busid) REFERENCES bus(bus_id),
    CONSTRAINT fk_schedule_fair FOREIGN KEY (fairid) REFERENCES fair(fairid)
);

CREATE INDEX idx_schedule_agencyid ON schedule(agencyid);
CREATE INDEX idx_schedule_route_id ON schedule(routeid);
CREATE INDEX idx_schedule_bus_id ON schedule(busid);
CREATE INDEX idx_schedule_date ON schedule(date);

-- ========================
-- Image Tables (S3 Storage)
-- ========================

-- Bus Image Table
CREATE TABLE IF NOT EXISTS bus_image (
    image_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bus_id UUID NOT NULL,
    s3_bucket_name VARCHAR(255) NOT NULL,
    s3_key VARCHAR(500) NOT NULL UNIQUE,
    image_url VARCHAR(1000),
    file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(100),
    file_size BIGINT,
    is_primary BOOLEAN DEFAULT FALSE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    CONSTRAINT fk_bus_image_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE
);

CREATE INDEX idx_bus_image_bus_id ON bus_image(bus_id);
CREATE INDEX idx_bus_image_s3_key ON bus_image(s3_key);
CREATE INDEX idx_bus_image_is_primary ON bus_image(bus_id, is_primary);

-- Driver Image Table
CREATE TABLE IF NOT EXISTS driver_image (
    image_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    driver_id UUID NOT NULL,
    s3_bucket_name VARCHAR(255) NOT NULL,
    s3_key VARCHAR(500) NOT NULL UNIQUE,
    image_url VARCHAR(1000),
    file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(100),
    file_size BIGINT,
    is_primary BOOLEAN DEFAULT FALSE,
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    CONSTRAINT fk_driver_image_driver FOREIGN KEY (driver_id) REFERENCES driver(driver_id) ON DELETE CASCADE
);

CREATE INDEX idx_driver_image_driver_id ON driver_image(driver_id);
CREATE INDEX idx_driver_image_s3_key ON driver_image(s3_key);
CREATE INDEX idx_driver_image_is_primary ON driver_image(driver_id, is_primary);
