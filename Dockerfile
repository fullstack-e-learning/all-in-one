# First stage: pull dependencies
FROM ubuntu AS dependencies

# Update package lists and install necessary packages
RUN apt-get update && \
    apt-get install -y wget && \
    apt-get clean

# Download the JAR file
RUN wget https://github.com/fullstack-e-learning/all-in-one/releases/download/v1.0.17/all-in-one-1.0.17.jar -O /app.jar

# Second stage: build final image
FROM openjdk:23-oraclelinux8

# Copy the JAR file from the previous stage
COPY --from=dependencies /app.jar /app/app.jar

# Set the working directory
WORKDIR /app

# Define the command to run the JAR file with Java
CMD ["java", "-jar", "app.jar"]