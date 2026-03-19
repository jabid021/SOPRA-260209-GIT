
//1) saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

//------------- Variables -------------
var posX = 0;
var posY = 0;
var mouvement = 30;
var pokemon = "pikachu";
var direction = "Down";
var anim = "1";

let dureeDefense = 5;
let dureeCooldown = (dureeDefense + 2) * 1000;
let invincible = false;
let lastDefense = 0;

const maxcoeur = 3;
let actualcoeur = 3;
let coeurContainer;

const pikachu = document.getElementById("pikachu");

const audioStart = document.getElementById("audioStart");
const audioTheme = document.getElementById("themePokemon");
const audioDeath = document.getElementById("audioDeath");
const audioMad = document.getElementById("audioMad");
const audioProtect = document.getElementById("audioProtect");
const audioWin = document.getElementById("audioWin");

let score = 0
let tempsScore


imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + anim + ".png");

inputName.onkeyup = checkBtnValidate;
btnStart.onclick = launchGame;

function launchGame() {
  //play audio
  audioStart.play();
  audioTheme.play();

  addCoeurs();
  imgPikachu.setAttribute("title", inputName.value);
  document.getElementById("formStart").style.setProperty("display", "none");
  document.getElementById("grass").style.setProperty("display", "flex");
  document.body.onkeydown = deplacement;

  for (let i = 0; i < virusNumber; i++) {
    createVirus();
  }

  tempsScore = setInterval(gagnerPoints, 2000);
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
  switch (anim) {
    case "1": anim = "2"; break;
    case "2": anim = "3"; break;
    case "3": anim = "4"; break;
    case "4": anim = "1"; break;
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
  } else if (event.key == " ") {
    getDefense();
  }

  posX = Math.max(0, Math.min(grass.offsetWidth - imgPikachu.offsetWidth, posX));
  posY = Math.max(0, Math.min(grass.offsetHeight - imgPikachu.offsetHeight, posY));

  pikachu.style.top = posY + "px";
  pikachu.style.left = posX + "px";
  imgPikachu.setAttribute("src", "assets/img/" + pokemon + direction + anim + ".png");
  if (invincible) {
    //dysplay condom
    condom.style.display = "flex";
    condom.style.position = "absolute";
    condom.style.width = 16 + pikachu.offsetWidth + "px";
    condom.style.height = 16 + pikachu.offsetHeight + "px";
    condom.style.top = posY - 8 + 20 - pikachu.offsetHeight + "px";
    condom.style.left = posX - 8 + "px";
  }
  updateCoeursPosition();
}

function getDefense(event) {
  /**
   * Configure the timer and cooldown of the protection of the pokemon
   * Update the value of invincible to true when countdown start
   */
  let setDuree = dureeDefense;

  if (lastDefense >= (Date.now() - dureeCooldown)) {
    console.log("No spam pls");
    return;
  }
  lastDefense = Date.now();

  newTimer = setInterval(() => { countdown(setDuree--); }, 1000);
  invincible = true;
  console.log("I AM INVINCIBLE - " + invincible)
}

function countdown(seconde) {
  /**
   * Countdown and values displayed on screen
   * Update the value of invincible to false when countdown reach zero
   */
  seconde--;

  messageAffiche = `${seconde} secondes...`;
  if (seconde <= 0) {
    messageAffiche = "Protection finie"
    timer.style.color = "red";
    clearInterval(newTimer);

    invincible = false;
    console.log("Oh shit... - " + invincible)
  }
  timer.innerHTML = messageAffiche;
}

function gagnerPoints() {
  /*
  Se lance toutes les x secondes pour ajouter un point au score
  */
  score++;
  console.log("score ?")
  document.getElementById("score").textContent = `💠 ${score}/20 💠`

  if (score === 20) {
    audioTheme.pause();
    audioWin.play()
    winner.style.setProperty("animation", "upGameOver 1s ease forwards")
    document.querySelectorAll(".virus").forEach(v => v.remove());
    clearInterval(tempsScore)
  }
}

function death() {
  /*
  En cas de mort
  Fonction moche à cause des délais d'attente entre les animations
  */
  audioTheme.pause();
  audioDeath.play();

  document.querySelectorAll(".virus").forEach(v => v.remove());


  clearInterval(tempsScore)
  const ball = document.getElementById("imgBall")

  sacha.style.setProperty("left", "0px")

  sachaLancement.addEventListener("transitionend", () => {
    let i = 2;
    const tempsEntreFrame = setInterval(() => {
      console.log("src", "assets/img/sacha_" + i + ".PNG")
      document.getElementById("imgSacha").setAttribute("src", "assets/img/sacha_" + i + ".PNG")
      i++;

      if (i === 4) {
        clearInterval(tempsEntreFrame)
        document.body.onkeydown = null;
        const posPikachuEcran = document.getElementById("imgPikachu").getBoundingClientRect()
        ball.style.setProperty("display", "flex")
        ball.style.setProperty("transform", " rotate(360deg)")
        ball.style.setProperty("left", (posPikachuEcran.x + imgPikachu.offsetWidth / 2 - ball.offsetWidth / 2) + "px")
        ball.style.setProperty("top", (posPikachuEcran.y + imgPikachu.offsetHeight / 2 - ball.offsetHeight / 2) + "px")
        ball.addEventListener("transitionend", () => {
          imgPikachu.style.setProperty("display", "none")
          gameOver.style.setProperty("animation", "upGameOver 1s ease forwards")
        }, { once: true });
      }
    }
      , 100)
  }, { once: true });
}