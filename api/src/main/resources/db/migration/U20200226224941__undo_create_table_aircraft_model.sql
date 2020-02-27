-- undo create_table_aircraft_model

drop table aircraft_model;

-- remove flyway migration version

delete from flyway_schema_history
where version = '20200226224941';
