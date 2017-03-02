ALTER TABLE dimensional_data_set RENAME TO data_set;

ALTER TABLE data_set RENAME COLUMN dimensional_data_set_id TO id;

ALTER TABLE data_set DROP COLUMN authentication_role;
ALTER TABLE data_set DROP COLUMN DISTRIBUTION;
ALTER TABLE data_set DROP COLUMN FREQUENCY;
ALTER TABLE data_set DROP COLUMN IDENTIFIER;
ALTER TABLE data_set DROP COLUMN ISSUED;
ALTER TABLE data_set DROP COLUMN json_metadata;
ALTER TABLE data_set DROP COLUMN KEYWORD;
ALTER TABLE data_set DROP COLUMN LANDINGPAGE;
ALTER TABLE data_set DROP COLUMN LANGUAGE;
ALTER TABLE data_set DROP COLUMN LICENSE;
ALTER TABLE data_set DROP COLUMN load_exception;
ALTER TABLE data_set DROP COLUMN MODIFIED;
ALTER TABLE data_set DROP COLUMN OBSCOUNT;
ALTER TABLE data_set DROP COLUMN PUBLISHER;
ALTER TABLE data_set DROP COLUMN SOURCE;
ALTER TABLE data_set DROP COLUMN SPATIAL;
ALTER TABLE data_set DROP COLUMN TEMPORAL;
ALTER TABLE data_set DROP COLUMN THEME;
ALTER TABLE data_set DROP COLUMN validation_exception;
ALTER TABLE data_set DROP COLUMN validation_message;
ALTER TABLE data_set DROP COLUMN reference_list;

ALTER TABLE dimension RENAME COLUMN dimensional_data_set_id TO data_set_id;

ALTER TABLE dimension_value RENAME COLUMN dimensional_data_set_id TO dimension_data_set_id;
ALTER TABLE dimension_value RENAME COLUMN name TO dimension_name;

ALTER TABLE dimension_datapoint RENAME TO dimension_value_datapoint;
ALTER TABLE dimension_value_datapoint RENAME COLUMN id TO datapoint_id;

ALTER TABLE file_status RENAME TO file;
ALTER TABLE job_file_status RENAME TO job_file;
ALTER TABLE job_file RENAME COLUMN job_job_id TO job_id;
ALTER TABLE job RENAME COLUMN job_id TO id;

ALTER TABLE dimensional_data_set_row_index RENAME COLUMN dimensional_data_set_id TO data_set_id;
ALTER TABLE dimensional_data_set_row_index RENAME TO data_set_row_index;
