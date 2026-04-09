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

# Permet de télécharger les dépendances (sans faire de compilation)
# > De garder du cache Docker si le fichier pom.xml ne change pas
# > L'option -B permet d'être en mode Batch (sans interaction utilisateur)
RUN mvn dependency:go-offline -B

COPY src src

RUN mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -DskipTests \
  -Dsonar.projectKey=quest-boot \
  -Dsonar.projectName='quest boot' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_034ef3b1066bb32ab3b9e8669db8eafdd1022a15


# Image finale, sans les sources, juste avec le fichier JAR qu'on récupère du builder au dessus
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.jar quest-boot.jar

EXPOSE 8080

CMD [ "java", "-jar", "quest-boot.jar" ]
