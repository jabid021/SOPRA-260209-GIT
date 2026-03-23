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
					<a class="btn btn-warning" href="ordinateur?numero=${ordinateur.numero}">Modifier</a>
					<a class="btn btn-danger" href="ordinateur?numero=${ordinateur.numero}&delete">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${ordinateur.numero==null}">
		<div class="message-form">Formulaire d'ajout</div>
	</c:if>
	
	<c:if test="${ordinateur.numero!=null}">
		<div class="message-form">Formulaire d'update (Ordinateur ${ordinateur.numero})</div>
	</c:if>
	
	<form action="ordinateur" method="post" class="form-clean">
	  <input type="hidden" name="numero" value="${ordinateur.numero}">
	
	  <label for="marque">Marque</label>
	  <input required="required" id="marque" type="text" name="marque" placeholder="Saisir la marque" value="${ordinateur.marque}">
	
	  <label for="ram">Ram</label>
	  <input required="required" id="ram" type="number" name="ram" min="0"  value="${ordinateur.ram}">
	  
	 
	  <label for="utilisateur">Utilisateur</label>
	  <select id="utilisateur" name="utilisateur.id">
	 	  <option value="">Choisir un stagiaire</option>
	 	  <c:forEach items="${stagiaires}" var="stagiaire">
	 	  	<c:choose>
		 	  	<c:when test="${stagiaire.id==ordinateur.utilisateur.id}"> <option selected value="${stagiaire.id}">Stagiaire ${stagiaire.id} - ${stagiaire.prenom} ${stagiaire.nom}</option></c:when>
		 	  	<c:otherwise><option value="${stagiaire.id}">Stagiaire ${stagiaire.id} - ${stagiaire.prenom} ${stagiaire.nom}</option></c:otherwise>
	 	  	</c:choose>
	 	  </c:forEach>
	  </select>
	 
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="ordinateur" class="btn btn-primary">Annuler</a>
	  </div>
	</form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>