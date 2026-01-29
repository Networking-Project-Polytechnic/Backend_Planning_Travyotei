-- Rename s3_key to public_id
ALTER TABLE bus_image RENAME COLUMN s3_key TO public_id;
ALTER TABLE driver_image RENAME COLUMN s3_key TO public_id;

-- Drop s3_bucket_name column as it is not needed for Cloudinary
ALTER TABLE bus_image DROP COLUMN s3_bucket_name;
ALTER TABLE driver_image DROP COLUMN s3_bucket_name;
