# all-in-one [ Monolith ]

> To run this application , we need to have a valid `application.yml` file with valid db details for CRUD operation and a local folder path to keep the uploaded file

```yaml
    spring:
      datasource:
        url: "jdbc:postgresql://<hostname>:<port>/<dbName>"
        username: "username"
        password: "password"
      file:
        base-path: "/path/to/store/file"
```

Or , the same details can be pass as System `ENV` variable like below :

```shell
    BASE_PATH = "/path/to/store/file"
    DB_HOST = "jdbc:postgresql://<hostname>:<port>/<dbName>"
    DB_USERNAME = "username"
    DB_PASSWORD = "password"
```

<details>
  <summary>Build & Run as a Jar file</summary>

  > Make sure Java is installed on the machine you are trying to run this
  
  ```shell
    ./mvnw clean install

    # TO RUN
    java -jar target/all-in-one-api-0.0.1-SNAPSHOT.jar
  ```
</details>

<details>
  <summary>Build & Run as a Docker Image</summary>

  > Make sure Java is installed on the machine you are trying to run this

  ```shell
    ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=all-in-one-api:0.0.1-SNAPSHOT
    
    # To RUN
    docker run -p 8080:8080 all-in-one-api:0.0.1-SNAPSHOT
  ```
</details>

<details>
  <summary>Build & Run as native image</summary>
  
  > Make sure you have GraalVm Jdk distro installed on the machine you are trying to run this.
  
  > This are native to OS. Meaning creating a native executable for Linux can be run Linux without any Jdk or Jre. same apply for Windows OS and Mac OS.

  ```shell
    ./mvnw native:compile -Pnative
    
    # TO RUN
    ./target/all-in-one-api
  ```
</details>


<details>
  <summary>Production this application!</summary>
    
  # Production this application (as .jar)
    
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
      DB_HOST = "jdbc:postgresql://<hostname>:<port>/<dbName>"
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
    
    - keep the `application.yml` file inside the /config folder
    
    ```shell
    .
    ├── all-in-one-1.0.18.jar
    └── config
        └── application.yml
    ```
    
    - The content of the `application.yml` file should be as follows:
    
    ```yaml
    spring:
      datasource:
        url: "jdbc:postgresql://<hostname>:<port>/<dbName>"
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
</details>



<details>
  <summary>Other</summary>
  
  - Inspired from spring security [csrf](https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-components) token usage guide

</details>
