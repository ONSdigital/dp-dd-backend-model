-- Job Creator API user. Needs CRUD permissions on its own job status tables, minimal SELECT permissions otherwise.
GRANT ALL PRIVILEGES ON TABLE data_resource, data_set to dd_editor;
