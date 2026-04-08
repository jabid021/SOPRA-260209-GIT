# Voir la liste des réseaux
docker network ls

# Créer un réseau
docker network create nom_rso

# Supprimer un réseau
docker network rm nom_rso
docker network rm id_rso

# Connecter un container existant au réseau
docker network connect nom_rso nom_container
docker network connect id_rso id_container

# Connecter un container existant d'un réseau
docker network disconnect nom_rso nom_container
docker network disconnect id_rso id_container

# Vérifier les infos d'un réseau (contrôler les containers dessus par exemple)
docker network inspect nom_rso
docker network inspect id_rso