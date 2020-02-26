# usaf-aircraft-api

Spring Boot Application

## API Specification

* `GET /aircraftModel`
* `GET /aircraftModel/:id`
* `GET /manufacturer`
* `GET /manufacturer/:id/aircraftModel`
* `GET /category/:categorySlug/aircraftModel`

## Entity-Relationship Diagram

Copy-paste the following into [http://nomnoml.com]:

```no-highlight
[AircraftModel | 
  name
  manufacturer_id
  category_id
  wikipedia_url
  image_url
]

[Manufacturer | 
  name
  location
]

[Manufacturer]->[AircraftModel]

[Category |
  name
]

[Category]->[AircraftModel]
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

## Generate App Base

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