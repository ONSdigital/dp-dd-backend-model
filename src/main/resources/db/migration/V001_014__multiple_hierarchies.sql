-- Remove the hierarchy FK from dimension
ALTER TABLE dimension DROP CONSTRAINT fk_dimension_hierarchy_id;
ALTER TABLE dimension DROP COLUMN hierarchy_id;
-- Add a generic dimension type column ("geography", "standard", etc).
ALTER TABLE dimension ADD COLUMN type VARCHAR(255) NOT NULL DEFAULT 'standard';

-- Add missing FK from dimension_value to dimension.
ALTER TABLE dimension_value ADD CONSTRAINT FK_dimension_value_dimension FOREIGN KEY (dimensional_data_set_id, name) REFERENCES dimension(dimensional_data_set_id, name);