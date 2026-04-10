# Se connecter au compte Docker
docker login -u username

# Se déconnecter du compte Docker
docker logout

# Tagguer une image existante (créer un nouveau nom d'image)
docker tag image_existante:tag username/nom_image:tag

# Publier l'image locale sur le Hub
docker push username/nom_image:tag
