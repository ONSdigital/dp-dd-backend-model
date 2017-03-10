-- Change the column type to text to allow larger meta data json to be stored.
ALTER TABLE data_resource ALTER COLUMN metadata TYPE text;