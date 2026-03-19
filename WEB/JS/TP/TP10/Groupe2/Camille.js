// A AJOUTER DANS LE HTML
<script defer src="Camille.js" charset="utf-8"></script>

// A AJOUTER DANS pikachu.js
const maxcoeur = 3;
let actualcoeur = 3;
let coeurContainer;

const pikachu = document.getElementById("pikachu");

// Fonction à appeler dans launchGame() du fichier pikachu.js
function addCoeurs() {
  coeurContainer = document.createElement("div");
  coeurContainer.id = "coeurContainer";
  coeurContainer.style.position = "absolute";
  coeurContainer.style.display = "flex";
  coeurContainer.style.top = "-25px";
  coeurContainer.style.left = "0px";

  for (let i = 0; i < maxcoeur; i++) {
    const coeur = document.createElement("span");
    coeur.textContent = "❤️";
    coeur.className = "coeur";
    coeur.style.fontSize = "20px";
    coeur.style.marginRight = "5px";
    coeurContainer.appendChild(coeur);
  }

  pikachu.appendChild(coeurContainer);
}

// Fonction pour perdre un cœur
function perdreCoeur() {
  if (actualcoeur > 0) {
    actualcoeur--;
    const coeurs = document.querySelectorAll(".coeur");
    coeurs[actualcoeur].textContent = "🤍";
  }
}

// Fonction à appeler à chaque déplacement pour que les coeurs suive Pikachu
function updateCoeursPosition() {
  if (coeurContainer) {
    coeurContainer.style.top = "-25px"; // reste au-dessus
    coeurContainer.style.left = "0px";
  }
}

// Fonction pour remettre les cœurs
function resetCoeurs() {
  actualcoeur = maxcoeur;
  const coeurs = document.querySelectorAll(".coeur");
  coeurs.forEach((coeur) => (coeur.textContent = "❤️"));
}
