<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des matieres</title>
<script
  src="https://code.jquery.com/jquery-4.0.0.js"
  integrity="sha256-9fsHeVnKBvqh3FB2HYu7g2xseAZ5MlN6Kz/qnkASV8U="
  crossorigin="anonymous"></script>

</head>
<body>
<%@ include file="/WEB-INF/securityAdmin.jsp" %>
<content>
<form class="form-clean"> <label for="filtrerLibelle">Filtrer</label><input id="filtrerLibelle" type="text" placeholder="Filtre matiere"></form>
	<table>
		<tr>
			<th>Id</th>
			<th>Libelle</th>
			<th>Actions</th>
		</tr>
		<tbody id="tbodyMatiere">
			<c:if test="${matieres.isEmpty()}"><tr><td align="center" colspan="3">AUCUNE MATIERE</td></tr></c:if>
			<c:forEach items="${matieres}" var="matiere">
			
				<tr>
					<td>${matiere.id}</td>
					<td>${matiere.libelle}</td>
					<td>
						<a class="btn btn-warning" href="matiere?id=${matiere.id}">Modifier</a>
						<a class="btn btn-danger" href="matiere?id=${matiere.id}&delete">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<c:choose>
		<c:when test="${matiere.id==null}"><div class="message-form">Formulaire d'ajout</div></c:when>
		<c:otherwise><div class="message-form">Formulaire d'update (Matiere ${matiere.id} - ${matiere.libelle})</div></c:otherwise>
	</c:choose>
	
	<form action="matiere" method="post" class="form-clean">
	  <input type="hidden" name="id" value="${matiere.id}">
	  <input type="hidden" name="version" value="${matiere.version}">
	  <label for="libelle">Libellé</label>
	  <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir le libellé" value="${matiere.libelle}">
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="matiere" class="btn btn-primary">Annuler</a>
	  </div>
	</form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>

<script>

filtrerLibelle.oninput = filtreAjax;


function filtreAjax()
{

	  $.ajax("matiere", {
		    type: "GET",
		    data: {
		      recherche: $("#filtrerLibelle" ).val()
		    },
		    success: function (resp) {
		    	$("#tbodyMatiere").html(resp);
		    	//tbodyMatiere.innerHTML=resp;
		    }
		  });

}


</script>