-- undo seed_table_category

delete from category where name in (
  'Combat Aircraft',
  'AWACS',
  'Electronic Warfare',
  'Reconnaissance',
  'Tanker',
  'Transport',
  'Helicopter',
  'Trainer Aircraft'
);

-- remove flyway migration version

delete from flyway_schema_history
where version = '20200226192153';
