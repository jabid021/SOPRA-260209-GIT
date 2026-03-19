const grass = document.getElementById("grass");
const pika = document.getElementById("imgPikachu");
const speed = 25;
const virusNumber = 10;
// TODO: spawn only on game start (maybe need a link with other js ?)
// TODO: add trigger to tell about the collision

const viruses = [];
function createVirus() {
  const imgVirus = document.createElement("img");
  imgVirus.width = 48;
  imgVirus.height = 48;
  imgVirus.style.position = "absolute";
  grass.appendChild(imgVirus);

  spawn(imgVirus);
  const virus = {
    img: imgVirus,
    dir: Math.floor(Math.random() * 4),
    stepsLeft: Math.floor(Math.random() * 20),
  };
  viruses.push(virus);
  setSpriteDirection(virus);
  setInterval(movement, speed, virus);
}

function spawn(img) {
  const h = grass.offsetHeight;
  const w = grass.offsetWidth;
  const half = grass.offsetHeight / 2;
  let posX = Math.random() * (w - img.offsetWidth);
  let posY = (Math.random() * h) / 2 + half - img.offsetHeight;
  console.log(`posX: ${posX} posY: ${posY}`);
  img.style.left = posX + "px";
  img.style.top = posY + "px";
}

function respawn(img) {
  const h = grass.offsetHeight;
  const w = grass.offsetWidth;
  console.log(`wi: ${w}, he: ${h}`);
  let posX;
  let posY;
  do {
    posX = Math.random() * (w - img.offsetWidth);
    posY = Math.random() * (h - img.offsetHeight);

    img.style.left = posX + "px";
    img.style.top = posY + "px";
  } while (isColliding(img, pika));
}

const eDirection = {
  LEFT: 0,
  TOP: 1,
  RIGHT: 2,
  BOTTOM: 3,
};

function isColliding(a, b) {
  const offset = 8;
  const aRect = a.getBoundingClientRect();
  const bRect = b.getBoundingClientRect();

  return !(
    aRect.bottom - offset < bRect.top + offset ||
    aRect.top + offset > bRect.bottom - offset ||
    aRect.right - offset < bRect.left + offset ||
    aRect.left + offset > bRect.right - offset
  );
}

function setSpriteDirection(virus) {
  switch (virus.dir) {
    case eDirection.LEFT:
      virus.img.src = "./assets/img/virus/virusWest.gif";
      break;
    case eDirection.TOP:
      virus.img.src = "./assets/img/virus/virusNorth.gif";
      break;
    case eDirection.RIGHT:
      virus.img.src = "./assets/img/virus/virusEast.gif";
      break;
    case eDirection.BOTTOM:
      virus.img.src = "./assets/img/virus/virusSouth.gif";
      break;
  }
}
function movement(virus) {
  const mov = 3;
  let posX = parseInt(virus.img.style.left);
  let posY = parseInt(virus.img.style.top);
  virus.stepsLeft--;
  if (virus.stepsLeft <= 0) {
    virus.dir = Math.floor(Math.random() * 4);
    virus.stepsLeft = Math.floor(Math.random() * 20);
    setSpriteDirection(virus);
  }
  switch (virus.dir) {
    case eDirection.LEFT:
      posX -= mov;
      break;
    case eDirection.TOP:
      posY -= mov;
      break;
    case eDirection.RIGHT:
      posX += mov;
      break;
    case eDirection.BOTTOM:
      posY += mov;
      break;
  }

  posX = Math.max(0, Math.min(grass.offsetWidth - virus.img.offsetWidth, posX));
  posY = Math.max(
    0,
    Math.min(grass.offsetHeight - virus.img.offsetHeight, posY),
  );
  virus.img.style.left = posX + "px";
  virus.img.style.top = posY + "px";
  if (isColliding(virus.img, pika)) {
    console.log("Collision");
    respawn(virus.img);
  }
}

for (let i = 0; i < virusNumber; i++) {
  createVirus();
}

