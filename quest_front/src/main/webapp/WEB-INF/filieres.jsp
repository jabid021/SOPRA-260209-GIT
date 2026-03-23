<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des filieres</title>
</head>
<body>
<%@ include file="/WEB-INF/securityAdmin.jsp" %>
<!--
<c:forEach begin="1" end="10" step="1" var="i">
	<h2>${i}</h2>
</c:forEach>
-->

<content>
	<table>
		<tr>
			<th>Id</th>
			<th>Libelle</th>
			<th>Date Debut</th>
			<th>Date Fin</th>
			<th>Actions</th>
		</tr>
		<c:if test="${filieres.isEmpty()}"><tr><td align="center" colspan="5">AUCUNE FILIERE</td></tr></c:if>
		
		<c:forEach items="${filieres}" var="filiere">
			<tr>
				<td>${filiere.id}</td>
				<td>${filiere.libelle}</td>
				<td>${filiere.debut}</td>
				<td>${filiere.fin}</td>
				<td>
					<a class="btn btn-warning" href="filiere?id=${filiere.id}">Modifier</a>
					<a class="btn btn-danger" href="filiere?id=${filiere.id}&delete">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${filiere.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
	</c:if>
	
	<c:if test="${filiere.id!=null}">
		<div class="message-form">Formulaire d'update (Filiere ${filiere.id} - ${filiere.libelle})</div>
	</c:if>
	
	<form action="filiere" method="post" class="form-clean">
	  <input type="hidden" name="id" value="${filiere.id}">
	
	  <label for="libelle">Libellé</label>
	  <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir le libellé" value="${filiere.libelle}">
	
	  <label for="debut">Date de début</label>
	  <input required="required" id="debut" type="date" name="debut" value="${filiere.debut}">
	
	  <label for="fin">Date de fin</label>
	  <input required="required" id="fin" type="date" name="fin" value="${filiere.fin}">
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="filiere" class="btn btn-primary">Annuler</a>
	  </div>
	</form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>