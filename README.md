# all-in-one-api

To Build (Jar file)
> Make sue , you have Java 21 install from the machine you are invoking these command.
```shell
./mvnw clean install
```
To Run the jar file:
```shell
java -jar target/all-in-one-api-0.0.1-SNAPSHOT.jar
```
> This might fail , as to run this application , it need a postgres db and the db information can be pass as an external property (see below ..)

To Build (docker Image)
```shell
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=all-in-one-api:0.0.1-SNAPSHOT
```

To run the docker image
```shell
docker run -p 8080:8080 all-in-one-api:0.0.1-SNAPSHOT
```
> This might fail , as to run this application , it need a postgres db and the db information can be pass as an external property (see below ..)

To Build (native image)
```shell
./mvnw native:compile -Pnative
```
To Run (native image)
```shell
./target/all-in-one-api
```
> This might fail , as to run this application , it need a postgres db and the db information can be pass as an external property (see below ..)


## To Run this application , We can follow several approach
- Environment Variable
This applicable for `jar` , `docker image` and `native image`.
```shell
BASE_PATH = "/path/to/store/file"
DB_HOST = "<protocol>://<db-host>:<5432>/dbname"
DB_USERNAME = "username"
DB_PASSWORD = "password"
```
- Jvm parameter
To run the jar file, the property file can be pass as `-D` parameter. 
```shell
# Download the latest release from this repository release section.
java -jar -Dspring.datasource.url=${DB_HOST} -Dspring.datasource.username=${DB_USERNAME} -Dspring.datasource.password=${DB_PASSWORD} -Dspring.file.base-path=${BASE_PATH} all-in-one-api-<VERSION>.jar
#OR
java -jar all-in-one-api-<VERSION>.jar
```
- externalise the config file
Make sure you need to have  file and folder structure like below.
```shell
.
├── all-in-one-1.0.17.jar
└── config
    └── application.yml
```
The content of the `application.yml` file should be as follows:

```yaml
spring:
  datasource:
    url: "<protocol>://<db-host>:<5432>/dbName"
    username: "username"
    password: "password"
  file:
    base-path: "/path/to/store/file"
```
then run the jar file as follows:

If your application.yml is somewhere else , you can also point that like below:
```shell
java -jar all-in-one-api-<VERSION>.jar --spring.config.location=classpath:/path/to/application.yml
```

# production this application (as .jar)
> Make sure you need to have CI/CD pipeline to build and deploy the application to the cloud or on-prem.

### Necessary Infrastructure
- Virtual Machine (VM) or Cloud Instance.
- Make sure you have JRE installed in the VM.
- File Storage (shared storage recommended for multiple instances)
- Database (PostgreSQL)
- Application Load Balancer (If we have multiple instances)
