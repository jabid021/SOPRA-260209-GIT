# ── Étape 1 : Build Angular ────────────────────────────────────────
FROM node:lts-alpine3.22 AS BUILD
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build

# ── Étape 2 : Web Server ────────────────────────────────────────
FROM nginx:stable-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build app/dist/Front/browser /usr/share/nginx/html
