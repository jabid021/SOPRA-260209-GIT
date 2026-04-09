# Builder une image à partir de Dockerfile (se placer dans le répertoire où ce trouve le Dockerfile)
docker build -t nom_image:tag_image .

# Builder une image à partir de Dockerfile ("C:/chemin/vers/contenu" est le répertoire où se trouve le Dockerfile)
docker build -t nom_image:tag_image C:/chemin/vers/contenu

# Exemple
docker build -t ajc/quest:boot .

# Nettoyer les images du build
docker image prune -f