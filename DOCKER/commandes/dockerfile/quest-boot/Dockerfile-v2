# FROM eclipse-temurin:21-jdk

# WORKDIR /app

# COPY . .

# RUN ./mvnw clean package -B -DskipTests

# RUN rm -R src
# RUN rm pom.xml

# EXPOSE 8080

# CMD [ "java", "-jar", "target/docker-boot-0.0.1-SNAPSHOT.jar" ]


# Container intermédiaire pour génération (compilation) du fichier JAR
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn package -DskipTests -B


# Image finale, sans les sources, juste avec le fichier JAR qu'on récupère du builder au dessus
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar quest-boot.jar

EXPOSE 8080

CMD [ "java", "-jar", "quest-boot.jar" ]
