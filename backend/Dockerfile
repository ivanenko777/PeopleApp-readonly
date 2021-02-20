### STAGE 1
FROM maven:3.6.3-jdk-11 as build
WORKDIR /app

COPY ./backend/pom.xml /app
COPY ./backend/src /app/src

RUN mvn -B -q -f pom.xml clean package -DskipTests

### STAGE 2
FROM openjdk:11-jdk-slim
EXPOSE 8080
COPY --from=build /app/target/*.jar app.jar
# ENTRYPOINT ["java","-jar","app.jar"]