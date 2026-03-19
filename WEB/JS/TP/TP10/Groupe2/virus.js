<script defer src="virus.js" charset="utf-8"></script>

let imgVirus = document.createElement("img");
imgVirus.src = "./assets/img/virus.png"
imgVirus.width = 64
imgVirus.height = 64
imgVirus.style.position = "absolute"
imgVirus.style.left = "30px"
imgVirus.style.top = "500px"
let grass = document.getElementById("grass")
grass.appendChild(imgVirus)
let pika = document.getElementById("imgPikachu")
let speed = 500


// TODO: spawn randomly in the bottom half
// TODO: add multiple virus (possibility to choose how much)
// TODO: spawn only on game start (maybe need a link with other js ?)
// TODO: check that it does not respawn on pika
// TODO: add trigger to tell about the collision 


function isColliding(a, b) {
    const offset = 10;
    const aRect = a.getBoundingClientRect();
    const bRect = b.getBoundingClientRect();

    return !(
        aRect.bottom - offset < bRect.top + offset ||
        aRect.top + offset > bRect.bottom - offset ||
        aRect.right - offset < bRect.left + offset ||
        aRect.left + offset > bRect.right - offset
    );
}

function respawn(img) {
    const h = grass.offsetHeight
    const w = grass.offsetWidth
    console.log(`wi: ${w}, he: ${h}`)
    let posX = Math.random() * (w - img.offsetWidth);
    let posY = Math.random() * (h - img.offsetHeight);


    img.style.left = posX + "px"
    img.style.top = posY + "px"
}

const eDirection = {
    LEFT: 0,
    TOP: 1,
    RIGHT: 2,
    BOTTOM: 3
}

function movement(img) {
    let mov = 30
    let posX = parseInt(img.style.left)
    let posY = parseInt(img.style.top)
    let dir = Math.floor(Math.random() * 4)
    switch (dir) {
        case eDirection.LEFT:
            posX -= mov
            break;
        case eDirection.TOP:
            posY -= mov
            break;
        case eDirection.RIGHT:
            posX += mov;
            break;
        case eDirection.BOTTOM:
            posY += mov;
            break;
    }

    posX = Math.max(0, Math.min(grass.offsetWidth - img.offsetWidth, posX));
    posY = Math.max(0, Math.min(grass.offsetHeight - img.offsetHeight, posY));
    imgVirus.style.left = posX + "px";
    imgVirus.style.top = posY + "px";
    if (isColliding(img, pika)) {
        console.log("Collision")
        respawn(img)
    }

}

setInterval(movement, speed, imgVirus)