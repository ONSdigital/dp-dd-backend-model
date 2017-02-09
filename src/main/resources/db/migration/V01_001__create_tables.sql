CREATE TABLE CATEGORY (category_id BIGINT NOT NULL, NAME VARCHAR(255), fk_category_id BIGINT, concept_system VARCHAR(255), PRIMARY KEY (category_id));
CREATE INDEX INDEX_CATEGORY_name_concept_system ON CATEGORY (name, concept_system);
CREATE TABLE concept_system (concept_system VARCHAR(255) NOT NULL, PRIMARY KEY (concept_system));
CREATE TABLE data_resource (data_resource VARCHAR(255) NOT NULL, column_concept VARCHAR(255), METADATA VARCHAR(255), row_concept VARCHAR(255), TITLE VARCHAR(255), PRIMARY KEY (data_resource));
CREATE TABLE DATAPOINT (ID uuid, data_marking VARCHAR(255), OBSERVATION numeric not null, observation_type_value numeric, PRIMARY KEY (ID));
CREATE TABLE dimension(dimensional_data_set_id uuid NOT NULL, name VARCHAR(255) NOT NULL, hierarchy_id uuid, PRIMARY KEY (dimensional_data_set_id, name));
CREATE TABLE dimension_value (ID uuid, dimensional_data_set_id uuid not null, NAME VARCHAR(255) NOT NULL, VALUE VARCHAR(255) NOT NULL, hierarchy_entry_id uuid, PRIMARY KEY (ID));
CREATE TABLE dimensional_data_point (data_marking VARCHAR(255), observation_type_value VARCHAR(255), VALUE DECIMAL(38), dimensional_data_set_id uuid, geographic_area_id BIGINT NOT NULL, time_period_id BIGINT NOT NULL, variable_id BIGINT NOT NULL, observation_type VARCHAR(255), PRIMARY KEY (dimensional_data_set_id, geographic_area_id, time_period_id, variable_id));
CREATE TABLE dimensional_data_set (dimensional_data_set_id uuid, authentication_role VARCHAR(255), DISTRIBUTION VARCHAR(255), FREQUENCY VARCHAR(255), IDENTIFIER VARCHAR(255), ISSUED VARCHAR(255), json_metadata VARCHAR(255), KEYWORD VARCHAR(255), LANDINGPAGE VARCHAR(255), LANGUAGE VARCHAR(255), LICENSE VARCHAR(255), load_exception VARCHAR(255), major_version INTEGER NOT NULL, METADATA text, minor_version INTEGER NOT NULL, MODIFIED VARCHAR(255), OBSCOUNT BIGINT, PUBLISHER VARCHAR(255), reference_list VARCHAR(255), revision_notes VARCHAR(255), revision_reason VARCHAR(255), s3_url VARCHAR(255), SOURCE VARCHAR(255), SPATIAL VARCHAR(255), STATUS VARCHAR(255), TEMPORAL VARCHAR(255), THEME VARCHAR(255), title VARCHAR(255), total_row_count BIGINT, validation_exception VARCHAR(255), validation_message VARCHAR(255), data_resource VARCHAR(255), PRIMARY KEY (dimensional_data_set_id));
CREATE TABLE dimensional_data_set_row_index (dimensional_data_set_id uuid, row_index BIGINT NOT NULL, PRIMARY KEY (dimensional_data_set_id, row_index));
CREATE TABLE geographic_area (geographic_area_id BIGINT NOT NULL, ext_code VARCHAR(255), METADATA VARCHAR(255), NAME VARCHAR(255), rel_geographic_area_id BIGINT, geographic_area_hierarchy VARCHAR(255), geographic_area_type VARCHAR(255), geographic_level_type VARCHAR(255), PRIMARY KEY (geographic_area_id));
CREATE INDEX INDEX_geographic_area_ext_code ON geographic_area (ext_code);
CREATE TABLE geographic_area_hierarchy (geographic_area_hierarchy VARCHAR(255) NOT NULL, PRIMARY KEY (geographic_area_hierarchy));
CREATE TABLE geographic_area_type (geographic_area_type VARCHAR(255) NOT NULL, PRIMARY KEY (geographic_area_type));
CREATE TABLE geographic_level_type (geographic_level_type VARCHAR(255) NOT NULL, METADATA VARCHAR(255), PRIMARY KEY (geographic_level_type));
CREATE TABLE hierarchy (ID VARCHAR(255) NOT NULL, NAME VARCHAR(255), TYPE VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE hierarchy_entry (ID uuid, CODE VARCHAR(255) NOT NULL, display_order INTEGER, NAME VARCHAR(255), hierarchy_id VARCHAR(255) NOT NULL, hierarchy_level_type_id VARCHAR(255), parent uuid, PRIMARY KEY (ID));
CREATE TABLE hierarchy_level_type (ID VARCHAR(255) NOT NULL, LEVEL INTEGER, NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE observation_type (observation_type VARCHAR(255) NOT NULL, NAME VARCHAR(255), PRIMARY KEY (observation_type));
CREATE TABLE POPULATION (geographic_area_ext_code VARCHAR(255), geographic_area_id BIGINT NOT NULL, time_period_id BIGINT NOT NULL, PRIMARY KEY (geographic_area_id, time_period_id));
CREATE TABLE PRESENTATION (presentation_id BIGINT NOT NULL, ACCESSURL VARCHAR(255), CONFORMSTO VARCHAR(255), DESCRIPTION VARCHAR(255), DOWNLOADURL VARCHAR(255), file_data BYTEA, file_name VARCHAR(255), file_size BIGINT, FORMAT VARCHAR(255), json_metadata VARCHAR(255), SPATIAL VARCHAR(255), TEMPORAL VARCHAR(255), TITLE VARCHAR(255), dimensional_data_set_id uuid, presentation_type VARCHAR(255), PRIMARY KEY (presentation_id));
CREATE TABLE presentation_type (presentation_type VARCHAR(255) NOT NULL, PRIMARY KEY (presentation_type));
CREATE TABLE subject_field (subject_field VARCHAR(255) NOT NULL, METADATA VARCHAR(255), rel_subject_field VARCHAR(255), PRIMARY KEY (subject_field));
CREATE TABLE time_period (time_period_id BIGINT NOT NULL, end_date DATE, NAME VARCHAR(255), start_date DATE, time_type VARCHAR(255), PRIMARY KEY (time_period_id));
CREATE INDEX INDEX_time_period_name ON time_period (name);
CREATE TABLE time_type (time_type VARCHAR(255) NOT NULL, PRIMARY KEY (time_type));
CREATE TABLE unit_type (unit_type VARCHAR(255) NOT NULL, PRIMARY KEY (unit_type));
CREATE TABLE value_domain (value_domain VARCHAR(255) NOT NULL, PRIMARY KEY (value_domain));
CREATE TABLE VARIABLE (variable_id BIGINT NOT NULL, ext_code VARCHAR(255), METADATA VARCHAR(255), NAME VARCHAR(255), unit_type VARCHAR(255), value_domain VARCHAR(255), PRIMARY KEY (variable_id));
CREATE INDEX INDEX_VARIABLE_name ON VARIABLE (name);
CREATE TABLE variable_category (category_id BIGINT NOT NULL, variable_id BIGINT NOT NULL, PRIMARY KEY (category_id, variable_id));
CREATE TABLE subject_field_concept_system (concept_system VARCHAR(255) NOT NULL, subject_field VARCHAR(255) NOT NULL, PRIMARY KEY (concept_system, subject_field));
CREATE TABLE dimension_datapoint (id uuid, dimension_value_id uuid, PRIMARY KEY (id, dimension_value_id));
CREATE TABLE dimensional_data_set_concept_system (dimensional_data_set_id uuid, concept_system VARCHAR(255) NOT NULL, PRIMARY KEY (dimensional_data_set_id, concept_system));
ALTER TABLE dimension_value ADD CONSTRAINT UNQ_dimension_value_0 UNIQUE (dimensional_data_set_id, name, value);
ALTER TABLE geographic_area ADD CONSTRAINT UNQ_geographic_area_0 UNIQUE (ext_code, geographic_area_hierarchy);
ALTER TABLE hierarchy_entry ADD CONSTRAINT UNQ_hierarchy_entry_0 UNIQUE (hierarchy_id, code);
ALTER TABLE CATEGORY ADD CONSTRAINT FK_CATEGORY_fk_category_id FOREIGN KEY (fk_category_id) REFERENCES CATEGORY (category_id);
ALTER TABLE CATEGORY ADD CONSTRAINT FK_CATEGORY_concept_system FOREIGN KEY (concept_system) REFERENCES concept_system (concept_system);
ALTER TABLE dimension_value ADD CONSTRAINT FK_dimension_value_hierarchy_entry_id FOREIGN KEY (hierarchy_entry_id) REFERENCES hierarchy_entry (ID);
ALTER TABLE dimensional_data_point ADD CONSTRAINT FK_dimensional_data_point_variable_id FOREIGN KEY (variable_id) REFERENCES VARIABLE (variable_id);
ALTER TABLE dimensional_data_point ADD CONSTRAINT FK_dimensional_data_point_observation_type FOREIGN KEY (observation_type) REFERENCES observation_type (observation_type);
ALTER TABLE dimensional_data_point ADD CONSTRAINT FK_dimensional_data_point_geographic_area_id FOREIGN KEY (geographic_area_id, time_period_id) REFERENCES POPULATION (geographic_area_id, time_period_id);
ALTER TABLE dimensional_data_point ADD CONSTRAINT FK_dimensional_data_point_dimensional_data_set_id FOREIGN KEY (dimensional_data_set_id) REFERENCES dimensional_data_set (dimensional_data_set_id);
ALTER TABLE dimensional_data_set ADD CONSTRAINT FK_dimensional_data_set_data_resource FOREIGN KEY (data_resource) REFERENCES data_resource (data_resource);
ALTER TABLE geographic_area ADD CONSTRAINT FK_geographic_area_geographic_area_type FOREIGN KEY (geographic_area_type) REFERENCES geographic_area_type (geographic_area_type);
ALTER TABLE geographic_area ADD CONSTRAINT FK_geographic_area_rel_geographic_area_id FOREIGN KEY (rel_geographic_area_id) REFERENCES geographic_area (geographic_area_id);
ALTER TABLE geographic_area ADD CONSTRAINT FK_geographic_area_geographic_area_hierarchy FOREIGN KEY (geographic_area_hierarchy) REFERENCES geographic_area_hierarchy (geographic_area_hierarchy);
ALTER TABLE geographic_area ADD CONSTRAINT FK_geographic_area_geographic_level_type FOREIGN KEY (geographic_level_type) REFERENCES geographic_level_type (geographic_level_type);
ALTER TABLE hierarchy_entry ADD CONSTRAINT FK_hierarchy_entry_parent FOREIGN KEY (parent) REFERENCES hierarchy_entry (ID);
ALTER TABLE hierarchy_entry ADD CONSTRAINT FK_hierarchy_entry_hierarchy_level_type_id FOREIGN KEY (hierarchy_level_type_id) REFERENCES hierarchy_level_type (ID);
ALTER TABLE hierarchy_entry ADD CONSTRAINT FK_hierarchy_entry_hierarchy_id FOREIGN KEY (hierarchy_id) REFERENCES hierarchy (ID);
ALTER TABLE POPULATION ADD CONSTRAINT FK_POPULATION_geographic_area_id FOREIGN KEY (geographic_area_id) REFERENCES geographic_area (geographic_area_id);
ALTER TABLE POPULATION ADD CONSTRAINT FK_POPULATION_time_period_id FOREIGN KEY (time_period_id) REFERENCES time_period (time_period_id);
ALTER TABLE PRESENTATION ADD CONSTRAINT FK_PRESENTATION_dimensional_data_set_id FOREIGN KEY (dimensional_data_set_id) REFERENCES dimensional_data_set (dimensional_data_set_id);
ALTER TABLE PRESENTATION ADD CONSTRAINT FK_PRESENTATION_presentation_type FOREIGN KEY (presentation_type) REFERENCES presentation_type (presentation_type);
ALTER TABLE subject_field ADD CONSTRAINT FK_subject_field_rel_subject_field FOREIGN KEY (rel_subject_field) REFERENCES subject_field (subject_field);
ALTER TABLE time_period ADD CONSTRAINT FK_time_period_time_type FOREIGN KEY (time_type) REFERENCES time_type (time_type);
ALTER TABLE VARIABLE ADD CONSTRAINT FK_VARIABLE_value_domain FOREIGN KEY (value_domain) REFERENCES value_domain (value_domain);
ALTER TABLE VARIABLE ADD CONSTRAINT FK_VARIABLE_unit_type FOREIGN KEY (unit_type) REFERENCES unit_type (unit_type);
ALTER TABLE variable_category ADD CONSTRAINT FK_variable_category_variable_id FOREIGN KEY (variable_id) REFERENCES VARIABLE (variable_id);
ALTER TABLE variable_category ADD CONSTRAINT FK_variable_category_category_id FOREIGN KEY (category_id) REFERENCES CATEGORY (category_id);
ALTER TABLE subject_field_concept_system ADD CONSTRAINT FK_subject_field_concept_system_subject_field FOREIGN KEY (subject_field) REFERENCES subject_field (subject_field);
ALTER TABLE subject_field_concept_system ADD CONSTRAINT FK_subject_field_concept_system_concept_system FOREIGN KEY (concept_system) REFERENCES concept_system (concept_system);
ALTER TABLE dimension_datapoint ADD CONSTRAINT FK_dimension_datapoint_id FOREIGN KEY (id) REFERENCES DATAPOINT (ID);
ALTER TABLE dimension_datapoint ADD CONSTRAINT FK_dimension_datapoint_dimension_value_id FOREIGN KEY (dimension_value_id) REFERENCES dimension_value (ID);
ALTER TABLE dimensional_data_set_concept_system ADD CONSTRAINT FK_dimensional_data_set_concept_system_dimensional_data_set_id FOREIGN KEY (dimensional_data_set_id) REFERENCES dimensional_data_set (dimensional_data_set_id);
ALTER TABLE dimensional_data_set_concept_system ADD CONSTRAINT FK_dimensional_data_set_concept_system_concept_system FOREIGN KEY (concept_system) REFERENCES concept_system (concept_system);
ALTER TABLE dimension ADD CONSTRAINT fk_dimension_dimensional_data_set_id FOREIGN KEY (dimensional_data_set_id) REFERENCES dimensional_data_set_id(dimensional_data_set_id);
ALTER TABLE dimension ADD CONSTRAINT fk_dimension_hierarchy_id FOREIGN KEY (hierarchy_id) REFERENCES hierarchy(id);
CREATE SEQUENCE presseq START WITH 1;
CREATE SEQUENCE timeseq START WITH 1;
CREATE SEQUENCE catseq START WITH 1;
CREATE SEQUENCE varseq START WITH 1;
CREATE SEQUENCE areaseq START WITH 1;
