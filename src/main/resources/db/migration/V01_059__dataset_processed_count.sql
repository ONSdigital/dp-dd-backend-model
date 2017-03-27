ALTER TABLE data_set ADD COLUMN processed_count INTEGER DEFAULT 0;

UPDATE data_set SET processed_count = COALESCE((select dspc.processed_count from data_set_processed_count dspc where dspc.data_set_id = id), total_row_count);

DROP TABLE  data_set_processed_count;
