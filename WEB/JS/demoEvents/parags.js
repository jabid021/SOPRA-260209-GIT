
titrePrincipal.onmouseover=demoEventSouris;

titrePrincipal.onmouseout=demoEventSouris;


titrePrincipal.onclick=demoEventSouris;

titrePrincipal.onmousedown = demoEventSouris;

titrePrincipal.onmouseup= demoEventSouris;


choixColor.oninput=function()
{
  paragPrincipal.style.color=choixColor.value;
}

choixColor.onfocus=function()
{
  paragPrincipal.style.border="solid black 2px";
}

choixColor.onblur=function()
{
  paragPrincipal.style.border="none";
}

btnResetColor.onclick=function()
{
  paragPrincipal.style.color="black";
  choixColor.value="#000000";
};




//la fonction demoEventSouris est à l'ecoute de certains events => Listener
function demoEventSouris(event)
{
  if(event.altKey==true)
  {
    console.log("event avec la touche ctrl press");
  }

  if(event.type=="click")
  {
    paragPrincipal.style.border="solid black 2px";
  }
  else if(event.type=="mouseover")
  {
      paragPrincipal.style.fontSize="35px";
  }
  else if(event.type=="mouseout")
  {
      paragPrincipal.style.fontSize="16px";
  }

}
