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
					<a class="btn btn-warning" href="filiere/${filiere.id}">Modifier</a>
					<a class="btn btn-danger" href="filiere/delete/${filiere.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${filiere.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
		<c:set value="filiere" var="chemin"/> 
	</c:if>
	
	<c:if test="${filiere.id!=null}">
		<div class="message-form">Formulaire d'update (Filiere ${filiere.id} - ${filiere.libelle})</div>
		<c:set value="filiere/${filiere.id}" var="chemin"/> 
	</c:if>
	
	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="filiere">
	  <form:hidden  path="id"/>
	
	  <label for="libelle">Libellé</label>
	  <form:input required="required" type="text" path="libelle" placeholder="Saisir le libellé"/>
	
	  <label for="debut">Date de début</label>
	  <form:input required="required" type="date" path="debut"/>
	
	  <label for="fin">Date de fin</label>
	  <form:input required="required" type="date" path="fin"/>
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="filiere" class="btn btn-primary">Annuler</a>
	  </div>
	</form:form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>