-- undo seed_table_manufacturer

delete from manufacturer;

-- remove flyway migration version

delete from flyway_schema_history
where version = '20200226201022';
