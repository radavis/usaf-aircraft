# usaf-aircraft-api

Spring Boot Application

## API Specification

* [x] `GET /aircraftModel`
* [x] `GET /aircraftModel/:id`
* [ ] `POST /aircraftModel`
* [ ] `GET /manufacturer`
* [ ] `GET /manufacturer/:id/aircraftModel`
* [ ] `GET /category/:categorySlug/aircraftModel`

## Entity-Relationship Diagram

Copy-paste the following into [http://nomnoml.com]:

```no-highlight
[aircraft_model | 
  name
  manufacturer_id
  category_id
  wikipedia_url
  image_url
]

[manufacturer | 
  name
  location
]

[manufacturer]->[aircraft_model]

[category |
  name
]

[category]->[aircraft_model]
```

## Store secrets in the environment

```bash
$ cp .env.example .env  # then, customize these variables, as necessary
$ source ./util/dotenv  # load the dotenv helper function into your current shell session
$ dotenv ./gradlew bootRun  # prefix commands that need secrets with `dotenv`
$ DOTENV=.env.test ./gradlew bootRun  # you can override loading the default `.env` file with another file
```

The `.env` file is ignored by git (see `.gitignore`). Store any secrets in this
file. Now, critical applicaiton secrets such as usernames and passwords are not
stored in source control.

## Setup MySQL for local development

**Note**: This configuration is appropriate for local development. A more secure
configuration should be used in production and other cloud environments.

```bash
$ mysql --version  # check if mysql is already installed
$ brew install mysql
$ brew services start mysql
$ echo $USER  # make a note of your username
$ mysql -uroot
mysql> CREATE USER 'your-username'@'localhost' IDENTIFIED WITH BY '';
mysql> GRANT ALL PRIVILEGES ON *.* TO 'your-username'@'localhost' WITH GRANT OPTION;
mysql> \q
```

[source](https://tableplus.com/blog/2018/10/how-to-create-a-superuser-in-mysql.html)

Now, that your local user account has permission to create mysql databases, run
the provided scripts.

```bash
$ dotenv ./util/db_create
$ dotenv ./util/db_info
```

## Create a database migration

```bash
$ ./util/db_create_migration your_migration_name  # e.g. - create_table_aircraft_models
```

Edit the generated files to include the necessary SQL statements:

```sql
-- ./src/main/resources/db/migration/VYYYYMMDDHHMMSS_create_table_aircraft_models.sql
-- create_table_AircraftModels
create table aircraft_models
  id serial,
  name varchar(255) not null,
  wikipedia_url text,
  image_url text
);
```

```sql
-- ./src/main/resources/db/migration/UYYYYMMDDHHMMSS_create_table_aircraft_models.sql
-- undo create_table_aircraft_models
drop table aircraft_models;
```

Run the migration:

```bash
$ dotenv ./gradlew flywayMigrate
```

Undo the migration:

```bash
$ dotenv ./util/db_undo_migration src/main/resources/db/migration/UYYYYMMDDHHMMSS_undo_migration_file.sql
```

## Generate App Base

How this Spring Boot app was initialized:

```bash
$ mkdir api && cd $_
$ curl https://start.spring.io/starter.zip \
    -d type=gradle-project \
    -d platformVersion=2.2.4.RELEASE \
    -d dependencies=data-jpa,flyway,lombok,mysql,web \
    -d groupId=mil.usaf.logux \
    -d artifactId=aircraftapi \
    -o starter.zip
$ unzip starter.zip
$ rm starter.zip
```

[source](https://docs.spring.io/initializr/docs/current/reference/html/#command-line)
