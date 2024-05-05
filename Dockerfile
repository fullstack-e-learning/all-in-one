FROM ubuntu

RUN apt-get update && apt-get install -y wget openjdk-21-jdk
RUN wget https://github.com/fullstack-e-learning/all-in-one/releases/download/v1.0.17/all-in-one-1.0.17.jar

CMD ["java", "-jar", "all-in-one-1.0.17.jar"]
