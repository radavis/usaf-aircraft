-- undo create_table_category

drop table category;

-- remove flyway migration version

delete from flyway_schema_history
where version = '20200226183112';
