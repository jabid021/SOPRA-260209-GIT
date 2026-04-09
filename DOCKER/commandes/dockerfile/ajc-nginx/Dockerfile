# On part de l'image nginx
FROM nginx:latest

# Se placer dans un répertoire de travail
WORKDIR /usr/share/nginx/html

# RUN ça permet d'exécuter des commandes "système" lors de la fabrication de l'image Docker
RUN apt update
RUN apt install vim wget -y

# Copie du fichier "index.html" (même endroit que le Dockerfile) vers /usr/share/nginx/html
# COPY index.html /usr/share/nginx/html/index.html
COPY index.html index.html
# COPY index.html .
# COPY . .

# Optionel - permet de dire à Docker le ou les ports qui pourront être bindés
EXPOSE 80
