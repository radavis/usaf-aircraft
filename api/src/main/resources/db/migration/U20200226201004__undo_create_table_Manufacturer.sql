-- undo create_table_manufacturer

drop table manufacturer;

-- remove flyway migration version

delete from flyway_schema_history
where version = '20200226201004';
