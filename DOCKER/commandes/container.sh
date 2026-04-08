# Récupérer une image du Docker Hub en local (si pas de tag, "latest" est utilisé)
docker pull image:tag

# Exécuter un nouveau container à partir d'une image (si pas de tag, "latest" est utilisé)
docker run image:tag

# Exécuter un nouveau container avec un binding de port
docker run -p portlocal:portcontainer image:tag
docker run -p 8080:80 --name nom_container image:tag # Exemple port local 8080 vers port container 80

# Exécuter un nouveau container avec un binding de port, un nom, en arrière plan (l'ordre des options n'a pas d'importance)
docker run -p portlocal:portcontainer -d --name nom_container image:tag

# Exécuter un nouveau container avec un binding de répertoire (volume)
docker run -v chemin_repertoire_local:chemin_repertoire_container --name nom_container image:tag
docker run -v D:/repertoire/du/systeme:/chemin/dans/container --name nom_container image:tag

# Exécuter un nouveau container à partir d'une image, en changeant la commande de base
docker run --name nom_container image:tag commande
docker run --name nom_container image:tag bash # Exemple

# Même chose avec le mode interactif
docker run -it --name nom_container image:tag commande
docker run -it --name nom_container image:tag bash # Exemple

# Exécuter une commande sur le container
docker exec nom_container commande
docker exec id_container commande
docker exec nom_container bash # Exemple

# Exécuter une commande sur le container en mode interactif
docker exec -it nom_container commande
docker exec -it id_container commande
docker exec -it nom_container bash # Exemple

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
