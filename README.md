# People App

![Alt text](/docs/screenshot.gif?raw=true "People App Screenshot")

## Task

1. Implement simple REST service based on spring-boot framework

   a. Service input personal id; date of birth

   b. Service output Person object with following properties:

   - Personal id
   - First name, last name
   - Gender: male, female
   - Date of birth

   c. Data need to be located in a database, e.g. H2

   d. Dependency manager: maven

   e. Log some service activities to log file and in database

   f. Implement unit tests and integration test

2. Implement angular client to use the implemented service

   a. Input form with search parameters

   b. Search button

   c. Result table

## Technologies and libraries

- Java 11 LTS
- Spring Boot 2.4.2
- Spring Framework 5.3.3
- h2 1.4.200
- Junit Jupiter 5.7.0
- NodeJs 14.15.5 LTS
- Angular 11.2.1
- rxjs 6.6.0
- tslib 2.0.0
- zone.js 0.11.3

## Try it with Docker

You need Docker installed

```
# Run app:
docker-compose build
docker-compose up -d
```

Open [http://localhost/](http://localhost/) in browser.

```
# Stop app:
docker-compose down
```
