
  var minute=0;
  var seconde=0;
  var timerInterval;

  btnStart.onclick=function()
  {
    btnStart.disabled=true;
    btnStop.disabled=false;
    timerInterval=setInterval(addSeconde,1000);
  };


  btnStop.onclick=function()
  {
    btnStart.disabled=false;
    btnStop.disabled=true;
    clearInterval(timerInterval);
  };

  btnReset.onclick=function()
  {
    minute=0;
    seconde=0;
    majAfficheTimer();
  }


  function addSeconde()
  {
      seconde++;
      if(seconde==60)
      {
        seconde=0;
        minute++;
        if(minute==60)
        {
          minute=0;
        }
      }
      majAfficheTimer();
  }

  function majAfficheTimer()
  {
    let minuteAffiche = (minute<10)? "0"+minute : ""+minute;
    let secondeAffiche = (seconde<10)? "0"+seconde : ""+seconde;
    chrono.innerHTML = `${minuteAffiche}:${secondeAffiche}`;
  }
