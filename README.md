# all-in-one-api

Build & Run a Jar file
```shell
./mvnw clean install
# RUN
java -jar target/all-in-one-api-0.0.1-SNAPSHOT.jar
```

Build & Run a native docker Image

```shell
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=all-in-one-api:0.0.1-SNAPSHOT

# RUN
docker run -p 8080:8080 all-in-one-api:0.0.1-SNAPSHOT
```

Build & Run native image
```shell
./mvnw native:compile -Pnative

# RUN
./target/all-in-one-api
```

# Preproduction this application (as .jar)

> Make sure you need to have CI/CD pipeline to build and deploy the application to the cloud or on-prem. 

### Necessary Infrastructure
- Virtual Machine (VM) or Cloud Instance.
- Make sure you have JRE installed in the VM.
- File Storage (shared storage recommended for multiple instances)
- Database (PostgreSQL)
- Application Load Balancer (If we have multiple instances)

> To run the jar file we need to have some Environment variable or the configuration file set up in the Instance (VM) : 

If we are interested to have the Environment variable , The details are as follows:
```shell
BASE_PATH = "/path/to/store/file"
DB_HOST = "<protocol>://<db-host>:<5432>"
DB_USERNAME = "username"
DB_PASSWORD = "password"
```
then run the jar file as follows:
```shell
# Download the latest release from this repository release section.
java -jar -Dspring.datasource.url=${DB_HOST} -Dspring.datasource.username=${DB_USERNAME} -Dspring.datasource.password=${DB_PASSWORD} -Dspring.file.base-path=${BASE_PATH} all-in-one-api-<VERSION>.jar
#OR
java -jar all-in-one-api-<VERSION>.jar
```

If we want to provide the configuration file insted of Environment variable `application.yml` then the content of the file should be as follows:
- make sure you need to have a folder called `/config` near to the jar file (like below tree structure)
```shell

```
- keep the `application.yml` file inside the /config folder
- The content of the `application.yml` file should be as follows:

```yaml
spring:
  datasource:
    url: "<protocol>://<db-host>:<5432>"
    username: "username"
    password: "password"
  file:
    base-path: "/path/to/store/file"
```
then run the jar file as follows:

```shell
# Download the latest release from this repository release section.
java -jar all-in-one-api-<VERSION>.jar --spring.config.location=classpath:/config/application.yml
#OR
java -jar all-in-one-api-<VERSION>.jar
```