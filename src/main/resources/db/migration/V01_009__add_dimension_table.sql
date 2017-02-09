CREATE TABLE dimension(
    dimensional_data_set_id uuid NOT NULL,
    name VARCHAR(255) NOT NULL,
    hierarchy_id uuid,
    PRIMARY KEY (dimensional_data_set_id, name),
    CONSTRAINT fk_dimension_dimensional_data_set_id FOREIGN KEY (dimensional_data_set_id) REFERENCES dimensional_data_set_id(dimensional_data_set_id),
    CONSTRAINT fk_dimension_hierarchy_id FOREIGN KEY (hierarchy_id) REFERENCES hierarchy(id)
);