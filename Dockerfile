# Use a base image
FROM openjdk:16-jdk-alpine

# Set working directory
WORKDIR /app

# Download the JAR file
ADD https://github.com/fullstack-e-learning/all-in-one/releases/download/v1.0.22/all-in-one-1.0.22.jar /app/all-in-one.jar

# Expose any necessary ports
EXPOSE 8080

# Environment variables for database connection
ENV DB_HOST=postgresdb \
    DB_PORT=5432 \
    DB_NAME=postgresdb \
    DB_USER=username \
    DB_PASS=password

# Command to run the application
# CMD ["java", "-jar", "all-in-one.jar"]
CMD ["java", "-jar", "all-in-one-1.0.22.jar", \
     "--db-host=$DB_HOST", \
     "--db-port=$DB_PORT", \
     "--db-name=$DB_NAME", \
     "--db-user=$DB_USER", \
     "--db-pass=$DB_PASS"]


# # Use a base image with Java 21
# FROM adoptopenjdk/openjdk16:alpine

# # Set the working directory
# WORKDIR /app

# # Copy the JAR file into the container
# COPY all-in-one-1.0.22.jar /app

# # Expose any ports your app needs
# EXPOSE 8080

# # Environment variables for database connection
# ENV DB_HOST=localhost \
#     DB_PORT=5432 \
#     DB_NAME=mydatabase \
#     DB_USER=myuser \
#     DB_PASS=mypassword

# # Command to run your application with database connection parameters
# CMD ["java", "-jar", "all-in-one-1.0.22.jar", \
#      "--db-host=$DB_HOST", \
#      "--db-port=$DB_PORT", \
#      "--db-name=$DB_NAME", \
#      "--db-user=$DB_USER", \
#      "--db-pass=$DB_PASS"]

