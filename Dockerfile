# Use a base image
FROM amazoncorretto:21.0.3-al2

# Set working directory
WORKDIR /app

# Download the JAR file
ADD https://github.com/fullstack-e-learning/all-in-one/releases/download/v1.0.22/all-in-one-1.0.22.jar .

ENTRYPOINT ["java", "-jar", "all-in-one-1.0.22.jar"]