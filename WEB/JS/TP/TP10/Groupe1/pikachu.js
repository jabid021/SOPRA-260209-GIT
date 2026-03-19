//1)OK saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2)OK Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3)OK Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4)OK Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5)OK Verifier que pikachu ne sort pas de la div grass

var pikachuXmin = 0;
var pikachuYmin = 0;
var pikachuXmax = pikachuXmin + 34;
var pikachuYmax = pikachuYmin + 34;
var pokeballXmin = 0;
var pokeballYmin = 0;
var pokeballXmax = pokeballXmin + 36;
var pokeballYmax = pokeballYmin + 36;
var minX = 0;
var minY = 0;
var maxX = 660;
var maxY = 660;
var mouvement = 30;
var pokemon = "pikachu";
var direction = "Down1";
imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + ".png");

btnWelcome.onclick = startForm;

btnStart.onclick = startGame;

btnReplay.onclick = restartGame;

  themePokemon.volume = 0.25;
  themeVictory.volume = 0.25;


document.getElementById("inputName").onkeyup = checkBtnValidate;

//Lancer le formulaire
function startForm() {
  welcome.style.setProperty("display", "none");
  formStart.style.setProperty("display", "flex");
  themePokemon.currentTime = 1;
  themePokemon.play();
}

function checkBtnValidate(event) {
  if (document.getElementById("inputName").value == "") {
    btnStart.disabled = true;
  } else {
    btnStart.disabled = false;
    if (event.key == "Enter") {
      startGame();
    }
  }
}

// Lancer le jeu
function startGame() {
  document
    .getElementById("pikachu")
    .setAttribute("title", document.getElementById("inputName").value);
  formStart.style.setProperty("display", "none");
  grass.style.setProperty("display", "flex");
  document.body.onkeydown = deplacement;
  shuffleTab(tabDirection);
  placePokeball();
}

// Placer la pokeball à une position aléatoire dans la div grass
function placePokeball() {
  pokeballXmin = 36+ Math.floor(Math.random() * 590);
  pokeballYmin = 36+ Math.floor(Math.random() * 590);
  pokeballXmax = pokeballXmin + 36;
  pokeballYmax = pokeballYmin + 36;
  console.log(pokeballXmin);
  console.log(pokeballYmin);
  document.getElementById("pokeball").style.top = pokeballYmin + "px";
  document.getElementById("pokeball").style.left = pokeballXmin + "px";
}

// Mélanger les directions pour que les touches soient différentes à chaque partie
var tabDirection = ["ArrowUp", "ArrowDown", "ArrowLeft", "ArrowRight"];
function shuffleTab(tab) {
  tab.sort(function () {
    return Math.random() - 0.5;
  });
}

// Deplacement de pikachu
function deplacement(event) {
  if (event.shiftKey == true) {
    mouvement = 100;
  } else {
    mouvement = 30;
  }
  if (event.key == tabDirection[1]) {
    pikachuYmin += mouvement;
    direction = (direction=="Down1"? "Down2": "Down1");

  } else if (event.key == tabDirection[3] ) {
    pikachuXmin += mouvement;
    direction = (direction=="Right1"? "Right2": "Right1");
  } else if (event.key == tabDirection[2] ) {
    pikachuXmin -= mouvement;
    direction = (direction=="Left1"? "Left2": "Left1");
  } else if (event.key == tabDirection[0]) {
    pikachuYmin -= mouvement;
    direction = (direction=="Up1"? "Up2": "Up1");
  }
  pikachuYmin = Math.max(0, pikachuYmin);
  pikachuYmin = Math.min(660, pikachuYmin);
  pikachuXmin = Math.max(0, pikachuXmin);
  pikachuXmin = Math.min(660, pikachuXmin);
  pikachuXmax = pikachuXmin + 34;
  pikachuYmax = pikachuYmin + 34;
  pikachu.style.top = pikachuYmin + "px";
  pikachu.style.left = pikachuXmin + "px";
  imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + ".png");

  if (
    pikachuXmin < pokeballXmax &&
    pikachuXmax > pokeballXmin &&
    pikachuYmin < pokeballYmax &&
    pikachuYmax > pokeballYmin
  ) {
    endOfGame();
  }
}

function endOfGame(event) {
  congratulations.style.setProperty("display", "grid");
  grass.style.setProperty("display", "none");
  themePokemon.pause();
  themeVictory.currentTime = 0.7;
  themeVictory.play();
}

function restartGame() {
  //On replace pikachu
  pikachuXmin = 0;
  pikachuYmin = 0;
  pikachuXmax = pikachuXmin + 36;
  pikachuYmax = pikachuYmin + 36;
  pikachu.style.top = pikachuYmin + "px";
  pikachu.style.left = pikachuXmin + "px";
  imgPikachu.setAttribute("src", "assets/img/" + pokemon + "Down.png");

  congratulations.style.setProperty("display", "none");
  grass.style.setProperty("display", "flex");
  document.body.onkeydown = deplacement;
  placePokeball();
  shuffleTab(tabDirection);
  themeVictory.pause();
  themePokemon.currentTime = 1;
  themePokemon.play();
}

// Etoiles de confusion au dessus de pikachu
const stars = [
  "assets/img/star1.png",
  "assets/img/star2.png",
  "assets/img/star3.png",
];
let index = 0;
setInterval(function () {
  index = (index + 1) % stars.length;
  document.querySelector("#star").src = stars[index];
}, 500);
