<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des ordinateurs</title>
</head>
<body>
<%@ include file="/WEB-INF/securityAdmin.jsp" %>
<content>
	<table>
		<tr>
			<th>Numero</th>
			<th>Marque</th>
			<th>Ram</th>
			<th>Utilisateur</th>
			<th>Actions</th>
		</tr>
		<c:if test="${ordinateurs.isEmpty()}"><tr><td align="center" colspan="5">AUCUN ORDINATEUR</td></tr></c:if>
		
		<c:forEach items="${ordinateurs}" var="ordinateur">
			<tr>
				<td>${ordinateur.numero}</td>
				<td>${ordinateur.marque}</td>
				<td>${ordinateur.ram}</td>
				<td>
				<c:choose>
					<c:when test="${ordinateur.utilisateur==null}">Ordinateur disponible</c:when>
					<c:otherwise>${ordinateur.utilisateur.id} - ${ordinateur.utilisateur.prenom} ${ordinateur.utilisateur.nom}</c:otherwise>
				</c:choose>
				</td>
				<td>
					<a class="btn btn-warning" href="ordinateur/${ordinateur.numero}">Modifier</a>
					<a class="btn btn-danger" href="ordinateur/delete/${ordinateur.numero}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${ordinateur.numero==null}">
		<div class="message-form">Formulaire d'ajout</div>
		<c:set var="chemin" value="ordinateur"/>
	</c:if>
	
	<c:if test="${ordinateur.numero!=null}">
		<div class="message-form">Formulaire d'update (Ordinateur ${ordinateur.numero})</div>
		<c:set var="chemin" value="ordinateur/${ordinateur.numero}"/>
	</c:if>
	
	<form:form action="ordinateur" method="post" class="form-clean" modelAttribute="ordinateur">
	  <form:hidden path="numero"/>
	
	  <label for="marque">Marque</label>
	  <form:input required="required" type="text" path="marque" placeholder="Saisir la marque"/>
	  <label for="ram">Ram</label>
	  <form:input required="required" type="number" path="ram" min="0" />
	  
	 
	  <form:label path="utilisateur.id">Utilisateur</form:label>
	  <form:select path="utilisateur.id">
	 	  <form:option value="" label="Choisir un stagiaire"/>
	 	  <form:options items="${stagiaires}" itemValue="id" itemLabel="infosSelect"/>
	  </form:select>
	 
	
	  <div class="form-actions">
	    <form:button class="btn btn-success">Sauvegarder</form:button>
	    <a href="ordinateur" class="btn btn-primary">Annuler</a>
	  </div>
	</form:form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>