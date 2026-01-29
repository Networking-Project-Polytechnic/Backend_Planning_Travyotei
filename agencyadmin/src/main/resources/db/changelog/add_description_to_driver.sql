-- Liquibase changeset antigravity:add_description_to_driver
-- Add description column to driver table

ALTER TABLE driver ADD COLUMN description TEXT;
