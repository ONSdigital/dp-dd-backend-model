-- Job Creator API user. Needs CRUD permissions on its own job status tables, minimal SELECT permissions otherwise.
GRANT ALL PRIVILEGES ON TABLE job, file_status, job_file_status TO job_creator;
GRANT SELECT ON TABLE dimensional_data_set TO job_creator; -- Needed to lookup S3 URL

-- Metadata API user. Needs read-only permissions on most of the schema.
GRANT SELECT ON ALL TABLES IN SCHEMA public TO dd_api;
REVOKE ALL ON TABLE job, file_status, job_file_status FROM dd_api;

-- Ensure data_discovery has CRUD permissions on all non-job-related tables.
GRANT ALL PRIVILEGES ON SCHEMA public TO data_discovery;
REVOKE ALL ON TABLE job, file_status, job_file_status FROM data_discovery;