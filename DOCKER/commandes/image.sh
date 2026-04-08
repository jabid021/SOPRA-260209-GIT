# Voir la liste des images présentes
docker image ls
docker images

# Supprimer une image
docker image rm nom_image:tag
docker image rm id_image
docker image rmi nom_image:tag
docker image rmi id_image

# Récupérer une image du Docker Hub
document pull image:tag