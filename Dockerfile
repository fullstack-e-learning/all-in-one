# First stage: pull dependencies
FROM ubuntu AS dependencies

# Update package lists and install necessary packages
RUN apt-get update && \
    apt-get install -y wget && \
    apt-get clean

# Download the JAR file
RUN wget https://github.com/fullstack-e-learning/all-in-one/releases/download/v1.0.17/all-in-one-1.0.17.jar -O /app.jar

# Create a directory for the application
RUN mkdir /app

# Move the JAR file to the app directory
RUN mv /app.jar /app/app.jar

# Use the official PostgreSQL image from Docker Hub for the second stage
FROM postgres:latest AS postgres

# Set environment variables
ENV POSTGRES_DB=allinone
ENV POSTGRES_USER=allinoneuser
ENV POSTGRES_PASSWORD=allinonepass
ENV POSTGRES_PORT=5432

# Expose PostgreSQL port
EXPOSE 5432

# Second stage: build final image
FROM openjdk:23-oraclelinux8

# Set woring directory
WORKDIR /app

# Copy the JAR file from the dependencies stage
COPY --from=dependencies /app /app

# Copy PostgreSQL JDBC driver
COPY --from=postgres /usr/share/java/postgresql.jar /app/postgresql.jar

# Expose the port your app runs on
EXPOSE 8080

# Define the command to run the JAR file with Java
CMD ["java", "-cp", ".:postgresql.jar", "-jar", "app.jar"]
