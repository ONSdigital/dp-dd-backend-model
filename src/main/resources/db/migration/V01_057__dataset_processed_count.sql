CREATE TABLE IF NOT EXISTS data_set_processed_count (data_set_id UUID, processed_count INTEGER, PRIMARY KEY (data_set_id));
ALTER TABLE data_set_processed_count ADD CONSTRAINT FK_data_set_processed_count_data_set_id FOREIGN KEY (data_set_id) REFERENCES data_set (id);

INSERT INTO data_set_processed_count (data_set_id, processed_count) SELECT data_set_id, count(*) FROM data_set_row_index GROUP BY data_set_id;

DROP TABLE  data_set_row_index;
