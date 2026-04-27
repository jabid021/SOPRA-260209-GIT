FROM nginx:latest

COPY nginx-default.conf /etc/nginx/conf.d/default.conf

COPY ./dist/quest-angular/browser /usr/share/nginx/html

EXPOSE 80

CMD [ "nginx", "-g", "daemon off;" ]
