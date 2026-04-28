FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY ./target/*.jar quest-boot.jar

EXPOSE 8080

CMD [ "java", "-jar", "quest-boot.jar" ]
