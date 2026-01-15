-- ========================
-- New Feature Tables
-- ========================

-- Vehicle Amenity Table
CREATE TABLE IF NOT EXISTS vehicle_amenity (
    amenity_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    amenity_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bus Can Transport Table
CREATE TABLE IF NOT EXISTS bus_can_transport (
    transport_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    item_name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bus Review Table
CREATE TABLE IF NOT EXISTS bus_review (
    review_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    bus_id UUID NOT NULL,
    customer_name VARCHAR(100),
    rating INTEGER CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bus_review_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE
);

CREATE INDEX idx_bus_review_bus_id ON bus_review(bus_id);

-- ========================
-- Join Tables (Many-to-Many)
-- ========================

-- Bus Amenities Join Table
CREATE TABLE IF NOT EXISTS bus_amenities (
    bus_id UUID NOT NULL,
    amenity_id UUID NOT NULL,
    PRIMARY KEY (bus_id, amenity_id),
    CONSTRAINT fk_bus_amenities_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE,
    CONSTRAINT fk_bus_amenities_amenity FOREIGN KEY (amenity_id) REFERENCES vehicle_amenity(amenity_id) ON DELETE CASCADE
);

-- Bus Transportables Join Table
CREATE TABLE IF NOT EXISTS bus_transportables (
    bus_id UUID NOT NULL,
    transport_id UUID NOT NULL,
    PRIMARY KEY (bus_id, transport_id),
    CONSTRAINT fk_bus_transportables_bus FOREIGN KEY (bus_id) REFERENCES bus(bus_id) ON DELETE CASCADE,
    CONSTRAINT fk_bus_transportables_item FOREIGN KEY (transport_id) REFERENCES bus_can_transport(transport_id) ON DELETE CASCADE
);
