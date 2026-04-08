# Récupérer une image du Docker Hub en local (si pas de tag, "latest" est utilisé)
docker pull image:tag

# Exécuter un nouveau container à partir d'une image (si pas de tag, "latest" est utilisé)
docker run image:tag

# Exécuter un nouveau container avec un binding de port
docker run -p portlocal:portcontainer image:tag

# Exécuter un nouveau container avec un binding de port, un nom, en arrière plan (l'ordre des options n'a pas d'importance)
docker run -p portlocal:portcontainer -d --name lenom image:tag

# Exécuter une commande sur le container
docker exec nom_container commande
docker exec id_container commande

# Exécuter une commande sur le container en mode interactif
docker exec -it nom_container commande
docker exec -it id_container commande

# Arrêter un container
docker stop nom_container
docker stop id_container

# Démarrer un container arrêté
docker start nom_container
docker start id_container

# Redémarrer un container
docker restart nom_container
docker restart id_container

# Inspecter un container
docker inspect nom_container
docker inspect id_container

# Supprimer un container arrêté
docker rm nom_container
docker rm id_container

# Supprimer un container démarré
docker rm -f nom_container
docker rm -f id_container

# Copier un fichier local vers un container
docker cp chemin/vers/fichier/local nom_container:/chemin/vers/fichier/dans/container
docker cp chemin/vers/fichier/local id_container:/chemin/vers/fichier/dans/container

# Copier un fichier du container vers le local
docker cp nom_container:/chemin/vers/fichier/dans/container chemin/vers/fichier/local
docker cp id_container:/chemin/vers/fichier/dans/container chemin/vers/fichier/local
