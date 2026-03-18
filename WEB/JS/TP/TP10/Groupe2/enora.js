// A AJOUTER DANS LE HTML
<script defer src="enora.js" charset="utf-8"></script>
<div id="sachaLancement">
  <div id="sacha"><img id="imgSacha" src="assets/img/sacha_1.PNG"></div>
  <div id="ball" ><img id="imgBall" src="assets/img/pokeball.png"></div>
</div>

// A AJOUTER EN CSS
#sacha
{
position: absolute;
left: -400px;
transition: left 1s ease;
z-index: 10;
}
#imgBall
{
display:none;
width: 48px; height: 48px;
position: absolute;
left: 320px; top: 450px;
transition: left 0.6s, top 0.6s;
z-index: 9;
}

function death(){
    const ball = document.getElementById("imgBall")

    sacha.style.setProperty("left", "0px")

    sachaLancement.addEventListener("transitionend", () => {
        let i = 2;
        const tempsEntreFrame = setInterval(() => {
            console.log("src", "assets/img/sacha_" + i + ".PNG")
            document.getElementById("imgSacha").setAttribute("src", "assets/img/sacha_" + i + ".PNG")
            i++;

            if(i===4) {
                clearInterval(tempsEntreFrame)
                document.body.onkeydown = null;
                const posPikachuEcran = document.getElementById("imgPikachu").getBoundingClientRect()
                ball.style.setProperty("display","flex")
                ball.style.setProperty("transform"," rotate(360deg)")
                ball.style.setProperty("left", (posPikachuEcran.x + imgPikachu.offsetWidth/2 - ball.offsetWidth/2) + "px")
                ball.style.setProperty("top", (posPikachuEcran.y + imgPikachu.offsetHeight/2 - ball.offsetHeight/2) + "px")
                ball.addEventListener("transitionend", () => {
                    imgPikachu.style.setProperty("display","none")
                }, { once: true });
            }
        }
        ,100)
    }, { once: true });
    
}