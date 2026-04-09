docker build -t ajc-quest-boot .
docker run -d -p 8080:8080 --network quest --name quest-boot -e SPRING_DATASOURCE_URL=jdbc:mysql://app-mysql:3306/projet_quest ajc-quest-boot