//1) saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posX = 0;
var posY = 0;
var mouvement = 30;
var pokemon = "pikachu";
var direction = "Down";
var anim = "1";
imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + anim + ".png");

inputName.onkeyup = checkBtnValidate;
btnStart.onclick = launchGame;

function launchGame() {
  imgPikachu.setAttribute("title", inputName.value);
  document.getElementById("formStart").style.setProperty("display", "none");
  document.getElementById("grass").style.setProperty("display", "flex");
  document.body.onkeydown = deplacement;
}

function checkBtnValidate(event) {
  if (inputName.value == "") {
    btnStart.disabled = true;
  } else {
    btnStart.disabled = false;
    if (event.key == "Enter") {
      launchGame();
    }
  }
}

function animState() {
  switch(anim){
    case "1" :anim = "2";break;
    case "2" :anim ="3";break;
    case "3" :anim ="4";break;
    case "4" :anim ="1";break;
  }
}

function deplacement(event) {
  if (event.key == "ArrowDown" || event.key == "s") {
    posY += mouvement;
    animState();
    direction = "Down";
  } else if (event.key == "ArrowRight" || event.key == "d") {
    posX += mouvement;
    animState();
    direction = "Right";
  } else if (event.key == "ArrowLeft" || event.key == "q") {
    posX -= mouvement;
    animState();
    direction = "Left";
  } else if (event.key == "ArrowUp" || event.key == "z") {
    posY -= mouvement;
    animState();
    direction = "Up";
  }

  posX = Math.max(0, Math.min(grass.offsetWidth - imgPikachu.offsetWidth, posX));
  posY = Math.max(0, Math.min(grass.offsetHeight - imgPikachu.offsetWidth, posY));

  pikachu.style.top = posY + "px";
  pikachu.style.left = posX + "px";
  imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + anim + ".png");
}
