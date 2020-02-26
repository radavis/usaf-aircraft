-- create_table_category

create table category (
  id serial,
  name varchar(255) not null unique,
  created_at timestamp
);
