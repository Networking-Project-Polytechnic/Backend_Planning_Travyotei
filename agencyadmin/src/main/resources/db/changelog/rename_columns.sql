DO $$
BEGIN
    -- 1. bus_type.busTypeId -> bus_type.bus_type_id
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'bus_type' AND column_name = 'busTypeId') THEN
        IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'bus_type' AND column_name = 'bus_type_id') THEN
            -- Target exists. Move data if valid and drop old.
            -- Using dynamic SQL to avoid parse errors if columns were missing in a different context (though checked above)
            EXECUTE 'UPDATE bus_type SET bus_type_id = "busTypeId" WHERE bus_type_id IS NULL';
            -- Drop with CASCADE to remove dependent constraints (old FKs)
            ALTER TABLE bus_type DROP COLUMN "busTypeId" CASCADE;
        ELSE
            -- Target does not exist. Safe to rename.
            ALTER TABLE bus_type RENAME COLUMN "busTypeId" TO bus_type_id;
        END IF;
    END IF;

    -- 2. bus_type.busTypeName -> bus_type.bus_type_name
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'bus_type' AND column_name = 'busTypeName') THEN
         IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'bus_type' AND column_name = 'bus_type_name') THEN
            EXECUTE 'UPDATE bus_type SET bus_type_name = "busTypeName" WHERE bus_type_name IS NULL';
            ALTER TABLE bus_type DROP COLUMN "busTypeName" CASCADE;
         ELSE
            ALTER TABLE bus_type RENAME COLUMN "busTypeName" TO bus_type_name;
         END IF;
    END IF;

    -- 3. bus.busTypeId -> bus.bus_type_id
    IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'bus' AND column_name = 'busTypeId') THEN
        IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'bus' AND column_name = 'bus_type_id') THEN
            EXECUTE 'UPDATE bus SET bus_type_id = "busTypeId" WHERE bus_type_id IS NULL';
            ALTER TABLE bus DROP COLUMN "busTypeId" CASCADE;
        ELSE
            ALTER TABLE bus RENAME COLUMN "busTypeId" TO bus_type_id;
        END IF;
    END IF;
END $$;
