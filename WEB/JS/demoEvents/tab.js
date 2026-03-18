
  function majFormulaire(id,nom,prenom)
  {
    document.getElementById("input-id").value=id;
    document.getElementById("input-nom").value=nom;
    document.getElementById("input-prenom").value=prenom;
  }


  btnAddPersonne.onclick=ajouterPersonneTab;

  document.getElementById("input-add-id").oninput=checkBtnValidate;
  document.getElementById("input-add-nom").oninput=checkBtnValidate;
  document.getElementById("input-add-prenom").oninput=checkBtnValidate;


  function checkBtnValidate()
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
  }
