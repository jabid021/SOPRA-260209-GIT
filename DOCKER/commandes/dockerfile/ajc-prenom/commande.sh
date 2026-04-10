docker login -u username

docker build -t username/ajc-prenom .

docker push username/ajc-prenom

docker run -d -p 80:80 --name ajc-prenom username/ajc-prenom
