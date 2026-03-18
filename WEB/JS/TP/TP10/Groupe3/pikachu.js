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
imgPikachu.setAttribute("height","100px");
imgPikachu.setAttribute("width","100px");
grass.setAttribute("height","700px");
grass.setAttribute("width","700px");

var leftMin = 0;
//var rightMax = document.getElementById("grass").clientWidth - document.getElementById("imgPikachu").clientWidth;
var rightMax = 600;
//console.log(document.getElementById("grass").getAttribute("width"));
//console.log(document.getElementById("imgPikachu").getAttribute("width"));
//console.log(rightMax);

var topMin = 0;
//var bottomMax = document.getElementById("grass").clientHeight - document.getElementById("imgPikachu").clientHeight;
var bottomMax = 600;
console.log(bottomMax);

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
  imgPikachu.setAttribute("title",name);
  grass.style.display="inline";
  formStart.style.display="none";
  document.body.onkeydown=deplacement;
}

btnStart.onclick=demarrerJeu;

function deplacement(event)
{
  if(event.key=="ArrowDown" || event.key=="s" )
  {
    if (posY + mouvement <= bottomMax)
    {
      posY += mouvement;
    }
    else
    {
      posY -= mouvement;
      setTimeout(function(){posY += mouvement;}, 700);
    }
    direction = "Down";
  }
  else if(event.key=="ArrowRight" || event.key=="d" )
  {
    if (posX + mouvement <= rightMax)
    {
      posX += mouvement;
    }
    else
    {
      posX -= mouvement;
      setTimeout(function(){posX += mouvement;}, 700);
    }
    direction = "Right";
  }

  else if(event.key=="ArrowLeft" || event.key=="q")
  {
    if(posX - mouvement >= leftMin)
      {
        posX -= mouvement;
      }
      else
      {
        posX += mouvement;
        setTimeout(function(){posX -= mouvement;}, 700);
      }
    direction = "Left";
  }

  else if(event.key=="ArrowUp" || event.key=="z")
  {
    if (posY - mouvement >= topMin){
      posY -= mouvement;
    }
    else
    {
      posY += mouvement;
      setTimeout(function(){posY -= mouvement;}, 700);
    }
    direction = "Up";
  }
  pikachu.style.top=posY+"px";
  pikachu.style.left=posX+"px";
  imgPikachu.setAttribute("src","assets/img/"+pokemon+direction+".png");

}
