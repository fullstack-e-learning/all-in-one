# all-in-one-api

### To build an docker image (spring native)

```shell
./mvnw spring-boot:build-image -Pnative
```

### To run the image

#### Native
```shell
./all-in-one-api

(or)

sh all-in-one-api
```

#### Docker 
```shell
docker run --rm -p 8080:8080 -e BASE_PATH=/tmp -v /tmp/upload:/tmp -u root all-in-one-api:0.0.1-SNAPSHOT
```