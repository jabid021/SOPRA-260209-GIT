function death(){
    audioTheme.pause();
    audioDeath.play();

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
                    gameOver.style.setProperty("animation","upGameOver 1s ease forwards")
                }, { once: true });
            }
        }
        ,100)
    }, { once: true });
    
}