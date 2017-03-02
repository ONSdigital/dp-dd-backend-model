ALTER TABLE file_status ADD COLUMN submitted_at TIMESTAMP(6) NOT NULL DEFAULT current_timestamp(6);
COMMENT ON COLUMN file_status.submitted_at IS 'Timestamp at which the file was submitted to be generated.';