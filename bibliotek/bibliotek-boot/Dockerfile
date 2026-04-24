# Container intermédiaire pour génération (compilation) du fichier JAR
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

# Permet de télécharger les dépendances (sans faire de compilation)
# > De garder du cache Docker si le fichier pom.xml ne change pas
# > L'option -B permet d'être en mode Batch (sans interaction utilisateur)
RUN mvn dependency:go-offline -B

COPY src src

RUN mvn package

# Image finale, sans les sources, juste avec le fichier JAR qu'on récupère du builder au dessus
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar quest-boot.jar

EXPOSE 8080

CMD [ "java", "-jar", "quest-boot.jar" ]
