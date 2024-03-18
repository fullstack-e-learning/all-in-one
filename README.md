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