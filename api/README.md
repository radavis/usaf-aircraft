# usaf-aircraft-api

Spring Boot Application

## API Specification

* `GET /aircraftModel`
* `GET /aircraftModel/:id`

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