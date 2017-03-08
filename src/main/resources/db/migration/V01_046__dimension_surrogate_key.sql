-- Add id PK table and populate with "random" UUIDs generated via MD5 (cheap hack, should be sufficient to avoid collisions)
ALTER TABLE dimension ADD COLUMN id UUID NOT NULL DEFAULT md5(random()::text)::uuid;
ALTER TABLE dimension_value DROP CONSTRAINT fk_dimension_value_dimension;
ALTER TABLE dimension DROP CONSTRAINT dimension_pkey;
ALTER TABLE dimension ADD PRIMARY KEY (id);

-- Add back in a unique constraint and index for the natural key as we will still use this for queries.
ALTER TABLE dimension ADD CONSTRAINT unq_dimension_data_set_id_name UNIQUE (data_set_id, name);

-- Add dimension FK from dimension value
ALTER TABLE dimension_value ADD COLUMN dimension_id UUID;
UPDATE dimension_value dv
   SET dimension_id = (SELECT d.id FROM dimension d WHERE d.name = dv.dimension_name AND d.data_set_id = dv.dimension_data_set_id);
ALTER TABLE dimension_value ALTER COLUMN dimension_id SET NOT NULL;

ALTER TABLE dimension_value DROP CONSTRAINT UNQ_dimension_value_0;
ALTER TABLE dimension_value DROP COLUMN dimension_name;
ALTER TABLE dimension_value DROP COLUMN dimension_data_set_id;

ALTER TABLE dimension_value ADD CONSTRAINT unq_dimension_value UNIQUE (dimension_id, value);
ALTER TABLE dimension_value ADD CONSTRAINT fk_dimension_value_dimension_id FOREIGN KEY (dimension_id) REFERENCES dimension(id);

