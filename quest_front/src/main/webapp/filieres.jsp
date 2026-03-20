<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<link rel="stylesheet" href="style.css">
<title>Gestion des filieres</title>
</head>
<body>

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
		<core:if test="${filieres.isEmpty()}"><tr><td align="center" colspan="5">AUCUNE FILIERE</td></tr></core:if>
		
		<core:forEach items="${filieres}" var="f">
			<tr>
				<td>${f.id}</td>
				<td>${f.libelle}</td>
				<td>${f.debut}</td>
				<td>${f.fin}</td>
				<td>
					<a class="btn btn-warning" href="filiere?id=${f.id}">Modifier</a>
					<a class="btn btn-danger" href="filiere?id=${f.id}&delete">Supprimer</a>
				</td>
			</tr>
		</core:forEach>
		
	</table>
	<core:if test="${filiere.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
	</core:if>
	
	<core:if test="${filiere.id!=null}">
		<div class="message-form">Formulaire d'update (Filiere ${filiere.id} - ${filiere.libelle})</div>
	</core:if>
	
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
	<a class="btn btn-info" href="index.jsp">Retour</a>
</content>
</body>
</html>