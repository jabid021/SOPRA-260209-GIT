//1) saisir le nom du pokemon et valider avec le bouton (le nom ne doit pas etre vide !)
//2) Masquer la div formStart, Afficher la div grass, mettre le nom du pokemon en title sur la div pikachu
//3) Gerer les deplacements, pouvoir bouger dans toutes les directions (haut,bas,gauche,droite) => les fleches et / ou zqsd
//4) Modifier la position de la div pikachu en fonction de la direction (+-30px par deplacement) et changer l'image
//5) Verifier que pikachu ne sort pas de la div grass

var posX=0;
var posY=0;
var mouvement=30;
var pokemon="pikachu";
var direction="Down";
imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");

var pikaWidth;
var pikaHeight;
var leftMin;
var rightMax;
var topMin;
var bottomMax;
var locked;

document.getElementById("inputName").onkeyup=checkBtnValidate;

function checkBtnValidate(event)
  {
    if(document.getElementById("inputName").value=="")
    {
        btnStart.disabled=true;
    }
    else
    {
        btnStart.disabled=false;
        if(event.key=="Enter")
        {
          demarrerJeu();
        }
    }
  }

function demarrerJeu()
{
  let name = document.getElementById("inputName").value;
  grass.setAttribute("height","700px");
  grass.setAttribute("width","700px");

  if(name == "gigamax")
  {
    imgPikachu.setAttribute("height","200px");
    imgPikachu.setAttribute("width","200px");
  }
  else
  {
    imgPikachu.setAttribute("height","40px");
    imgPikachu.setAttribute("width","40px");
  }


  pikaWidth = parseInt(document.getElementById("imgPikachu").getAttribute("width").slice(0,-2));
  pikaHeight = parseInt(document.getElementById("imgPikachu").getAttribute("height").slice(0,-2));

  leftMin = 0;
  //var rightMax = document.getElementById("grass").clientWidth - document.getElementById("imgPikachu").clientWidth;
  rightMax = 700-pikaWidth;
  topMin = 0;
  //var bottomMax = document.getElementById("grass").clientHeight - document.getElementById("imgPikachu").clientHeight;
  bottomMax = 700-pikaHeight;
  locked = false;


  pikachu.setAttribute("title",name);
  grass.style.display="grid";
  grass.style.gridArea="2 / 2 / 3 / 7";
  formStart.style.display="none";
  document.body.onkeydown=deplacement;
}

btnStart.onclick=demarrerJeu;

function deplacement(event)
{
  if((event.key=="ArrowDown" || event.key=="s") && !locked)
  {
    if (posY + mouvement <= bottomMax)
    {
      posY += mouvement;
    }
    else
    {
      pikaille.play()
      locked = true;
      posY -= mouvement;
      setTimeout(function(){posY += mouvement;pikachu.style.top=posY+"px";locked=false;}, 100);
    }
    direction = "Down";
  }
  else if((event.key=="ArrowRight" || event.key=="d") && !locked)
  {
    if (posX + mouvement <= rightMax)
    {
      posX += mouvement;
    }
    else
    {
      pikaille.play();
      locked = true;
      posX -= mouvement;
      setTimeout(function(){posX += mouvement;pikachu.style.left=posX+"px";locked=false;}, 100);
    }
    direction = "Right";
  }

  else if((event.key=="ArrowLeft" || event.key=="q") && !locked)
  {
    if(posX - mouvement >= leftMin)
      {
        posX -= mouvement;
      }
      else
      {
        pikaille.play();
        locked = true;
        posX += mouvement;
        setTimeout(function(){posX -= mouvement;pikachu.style.left=posX+"px";locked=false;}, 100);
      }
    direction = "Left";
  }

  else if((event.key=="ArrowUp" || event.key=="z") && !locked)
  {
    if (posY - mouvement >= topMin){
      posY -= mouvement;
    }
    else
    {
      pikaille.play();
      locked = true;
      posY += mouvement;
      setTimeout(function(){posY -= mouvement;pikachu.style.top=posY+"px";locked=false;}, 100);
    }
    direction = "Up";
  }
  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");

}
