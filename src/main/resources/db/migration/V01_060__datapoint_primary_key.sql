-- add data_set_id, row_index to datapoint table and populate them
ALTER TABLE datapoint ADD COLUMN data_set_id UUID, ADD COLUMN row_index INTEGER;

UPDATE datapoint dp SET data_set_id =
	(SELECT DISTINCT ds.id FROM data_set ds INNER JOIN dimension dim ON ds.id = dim.data_set_id
											INNER JOIN dimension_value dv ON dim.id = dv.dimension_id
											INNER JOIN dimension_value_datapoint dvd ON dvd.dimension_value_id = dv.id
											WHERE dvd.datapoint_id = dp.id);

-- For existing datasets we have no option but to assign row index values that don't correspond to the actual row index, and will actually be > the number of rows.
CREATE SEQUENCE datapoint_row_index_temp;

UPDATE datapoint SET row_index = NEXTVAL('datapoint_row_index_temp');

DROP SEQUENCE datapoint_row_index_temp;

-- replace the primary key on datapoint
ALTER TABLE dimension_value_datapoint DROP CONSTRAINT fk_dimension_datapoint_id;

ALTER TABLE datapoint DROP CONSTRAINT datapoint_pkey;

ALTER TABLE datapoint ADD PRIMARY KEY (data_set_id, row_index);

-- create a new join table dimension_value_row_index to replace dimension_value_datapoint
CREATE TABLE dimension_value_row_index AS
    (SELECT dp.data_set_id as data_set_id, dp.row_index as row_index, dvd.dimension_value_id as dimension_value_id
     FROM dimension_value_datapoint dvd INNER JOIN datapoint dp ON dvd.datapoint_id = dp.id);

ALTER TABLE dimension_value_row_index ADD PRIMARY KEY (data_set_id, row_index, dimension_value_id);

ALTER TABLE dimension_value_row_index ADD CONSTRAINT fk_dimension_value_datapoint_dv FOREIGN KEY (dimension_value_id) REFERENCES dimension_value;

ALTER TABLE dimension_value_row_index ADD CONSTRAINT fk_dimension_value_datapoint_datapoint FOREIGN KEY (data_set_id, row_index) REFERENCES datapoint;

-- drop the existing dimension_value_datapoint table, and rename dimension_value_row_index
DROP TABLE dimension_value_datapoint;

ALTER TABLE dimension_value_row_index RENAME TO dimension_value_datapoint;

ALTER TABLE datapoint DROP COLUMN id;