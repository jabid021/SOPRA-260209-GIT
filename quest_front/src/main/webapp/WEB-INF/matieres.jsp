<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des matieres</title>
</head>
<body>

<content>
	<table>
		<tr>
			<th>Id</th>
			<th>Libelle</th>
			<th>Actions</th>
		</tr>
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
	<a class="btn btn-info" href="index.jsp">Retour</a>
</content>
</body>
</html>