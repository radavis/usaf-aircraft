-- create_table_manufacturer

create table manufacturer (
  id serial,
  name varchar(255) not null unique,
  location varchar(255),
  created_at timestamp
);
