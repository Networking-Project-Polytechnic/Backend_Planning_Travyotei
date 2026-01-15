-- ========================
-- Refactor Route and Fair
-- ========================

-- 1. Route Stop Points (Collection Table)
CREATE TABLE IF NOT EXISTS route_stop_points (
    route_id UUID NOT NULL,
    stop_point VARCHAR(255) NOT NULL,
    CONSTRAINT fk_route_stop_points FOREIGN KEY (route_id) REFERENCES route(routeid) ON DELETE CASCADE
);

-- 2. Route Price Simplification
ALTER TABLE route_price DROP COLUMN IF EXISTS valid_from;
ALTER TABLE route_price DROP COLUMN IF EXISTS valid_to;

-- 3. Schedule Refactoring
-- Rename fairid to priceid in schedule table (if it exists)
DO $$
BEGIN
    -- Rename column if exists
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'schedule' AND column_name = 'fairid') THEN
        ALTER TABLE schedule RENAME COLUMN fairid TO priceid;
    END IF;

    -- Make priceid nullable temporarily to handle migration
    ALTER TABLE schedule ALTER COLUMN priceid DROP NOT NULL;

    -- Drop old foreign key if exists
    IF EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'fk_schedule_fair' AND table_name = 'schedule') THEN
        ALTER TABLE schedule DROP CONSTRAINT fk_schedule_fair;
    END IF;

    -- Drop old unique constraint if exists
    IF EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'uq_schedule_route_bus_fair' AND table_name = 'schedule') THEN
        ALTER TABLE schedule DROP CONSTRAINT uq_schedule_route_bus_fair;
    END IF;

    -- DATA MIGRATION: Update priceid based on route_id and bus_id
    -- This assumes that for each schedule, there is at least one price defined for that route and bus.
    -- If there are multiple prices for the same route and bus, we grab the first one.
    UPDATE schedule s
    SET priceid = (
        SELECT price_id 
        FROM route_price rp 
        WHERE rp.route_id = s.routeid AND rp.bus_id = s.busid 
        LIMIT 1
    );

    -- Clean up orphaned schedules that don't have a matching price
    -- If we don't do this, the NOT NULL constraint or FK will fail.
    DELETE FROM schedule WHERE priceid IS NULL;

    -- Restore NOT NULL constraint
    ALTER TABLE schedule ALTER COLUMN priceid SET NOT NULL;

    -- Add new unique constraint
    IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'uq_schedule_route_bus_price' AND table_name = 'schedule') THEN
        ALTER TABLE schedule ADD CONSTRAINT uq_schedule_route_bus_price UNIQUE (routeid, busid, date, priceid);
    END IF;

    -- Add new foreign key to route_price
    IF NOT EXISTS (SELECT 1 FROM information_schema.table_constraints WHERE constraint_name = 'fk_schedule_price' AND table_name = 'schedule') THEN
        ALTER TABLE schedule ADD CONSTRAINT fk_schedule_price FOREIGN KEY (priceid) REFERENCES route_price(price_id);
    END IF;
END $$;

-- 4. Delete Fair related tables (if they exist)
DROP TABLE IF EXISTS fair CASCADE;
