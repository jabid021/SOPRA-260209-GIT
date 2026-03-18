
  function majFormulaire(id,nom,prenom)
  {
    document.getElementById("input-id").value=id;
    document.getElementById("input-nom").value=nom;
    document.getElementById("input-prenom").value=prenom;
  }


  btnAddPersonne.onclick=ajouterPersonneTab;

  document.getElementById("input-add-id").onkeyup=checkBtnValidate;
  document.getElementById("input-add-nom").onkeyup=checkBtnValidate;
  document.getElementById("input-add-prenom").onkeyup=checkBtnValidate;


  function checkBtnValidate(event)
  {
    if(document.getElementById("input-add-id").value=="" || document.getElementById("input-add-nom").value=="" || document.getElementById("input-add-prenom").value=="" )
    {
        btnAddPersonne.disabled=true;
        statutForm.style.backgroundColor="red";
    }
    else
    {
        btnAddPersonne.disabled=false;
        statutForm.style.backgroundColor="green";
        if(event.key=="Enter")
        {
          ajouterPersonneTab();
        }
    }
  }

  function ajouterPersonneTab()
  {
    let id = document.getElementById("input-add-id").value;
    let nom = document.getElementById("input-add-nom").value;
    let prenom = document.getElementById("input-add-prenom").value;

    let nouvelleLigne =
    `<tr>
      <td>${id}</td>
      <td>${nom}</td>
      <td>${prenom}</td>
      <td><input type="button" onClick="majFormulaire(${id},'${nom}','${prenom}')" value="Modifier"></td>
    </tr>`

    corpTab.innerHTML +=nouvelleLigne;

    document.getElementById("input-add-id").value="";
    document.getElementById("input-add-nom").value="";
    document.getElementById("input-add-prenom").value="";

    btnAddPersonne.disabled=true;

    statutForm.style.backgroundColor="red";
  }
