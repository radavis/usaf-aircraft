# usaf-aircraft-api

Spring Boot Application

## API Specification

* `GET /aircraft`
* `GET /aircraft/:id`

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