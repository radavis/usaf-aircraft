-- create_table_aircraft_model

create table aircraft_model (
  id serial,
  name varchar(255) not null unique,
  manufacturer_id bigint unsigned not null references manufacturer(id),
  category_id bigint unsigned not null references category(id),
  wikipedia_url text,
  image_url text,
  created_at timestamp,
  updated_at timestamp
);
