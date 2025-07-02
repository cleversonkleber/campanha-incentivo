FROM maven:3.9.9-eclipse-temurin-21-jammy
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline  
COPY src ./src
RUN mvn clean install -DskipTests 

FROM eclipse-temurin:21-jre-jammy

COPY target/campanha-insentivo-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]