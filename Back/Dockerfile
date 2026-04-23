# ── Étape 1 : Build ─────────────────────────────────────────
# Conteneur intermédiaire pour la compilation du JAR
FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

# Copie uniquement le pom.xml en premier pour profiter du cache Docker :
# si le pom.xml ne change pas, les dépendances ne sont pas re-téléchargées.
COPY pom.xml .

# Téléchargement des dépendances hors-ligne (sans compilation)
# -B : mode Batch (pas d'interaction utilisateur)
RUN mvn dependency:go-offline -B

# Copie des sources uniquement après le téléchargement des dépendances
COPY src src

# Compilation et packaging du JAR
RUN mvn package -DskipTests -B


# ── Étape 2 : Image finale ───────────────────────────────────
# Image allégée : pas de sources, pas de Maven, uniquement le JAR
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Récupération du JAR produit par le builder
COPY --from=builder /app/target/*.jar biblio.jar

# Port exposé par l'API REST Spring Boot
EXPOSE 8080

# Lancement de l'application
CMD ["java", "-jar", "biblio.jar"]