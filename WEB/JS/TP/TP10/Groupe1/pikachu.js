//1)OK saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2)OK Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3)OK Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4)OK Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5)OK Verifier que pikachu ne sort pas de la div grass

var posX = 0;
var posY = 0;
var minX = 0;
var minY = 0;
var maxX = 660;
var maxY = 660;
var mouvement = 30;
var pokemon = "pikachu";
var direction = "Down";
imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + ".png");

btnStart.onclick = startGame;

document.getElementById("inputName").onkeyup = checkBtnValidate;

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

function startGame() {
  console.log(document.getElementById("inputName").value);
  document
    .getElementById("pikachu")
    .setAttribute("title", document.getElementById("inputName").value);
  console.log("startGame");
  formStart.style.setProperty("display", "none");
  grass.style.setProperty("display", "flex");
  document.body.onkeydown = deplacement;
}

function deplacement(event) {
  if (event.shiftKey == true) {
    mouvement = 100;
  } else {
    mouvement = 30;
  }
  if (event.key == "ArrowDown" || event.key == "s") {
    posY += mouvement;
    direction = "Down";
  } else if (event.key == "ArrowRight" || event.key == "d") {
    posX += mouvement;
    direction = "Right";
  } else if (event.key == "ArrowLeft" || event.key == "q") {
    posX -= mouvement;
    direction = "Left";
  } else if (event.key == "ArrowUp" || event.key == "z") {
    posY -= mouvement;
    direction = "Up";
  }
  posY = Math.max(0, posY);
  posY = Math.min(660, posY);
  posX = Math.max(0, posX);
  posX = Math.min(660, posX);
  pikachu.style.top = posY + "px";
  pikachu.style.left = posX + "px";
  imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + ".png");
}
