<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des formateurs</title>
</head>
<body>
<%@ include file="/WEB-INF/securityAdmin.jsp" %>
<content>
	<table>
		<tr>
			<th>Id</th>
			<th>Login</th>
			<th>Password</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Civilite</th>
			<th>Role</th>
			<th>Actions</th>
		</tr>
		<c:if test="${formateurs.isEmpty()}"><tr><td align="center" colspan="8">AUCUN FORMATEUR</td></tr></c:if>
		
		<c:forEach items="${formateurs}" var="formateur">
			<tr>
				<td>${formateur.id}</td>
				<td>${formateur.login}</td>
				<td>${formateur.password}</td>
				<td>${formateur.nom}</td>
				<td>${formateur.prenom}</td>
				<td>${formateur.civilite}</td>
				<td>${(formateur.admin) ? "ADMIN" : "FORMATEUR STANDARD"}</td>
				<td>
					<a class="btn btn-warning" href="formateur/${formateur.id}">Modifier</a>
					<a class="btn btn-danger" href="formateur/delete/${formateur.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${formateur.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
	</c:if>
	
	<c:if test="${formateur.id!=null}">
		<div class="message-form">Formulaire d'update (Formateur ${formateur.id})</div>
	</c:if>
	
	<form action="formateur${formateur.id == null ? '' : '/'.concat(formateur.id)}" method="post" class="form-clean">
	  <input type="hidden" name="id" value="${formateur.id}">
	
	  <label for="login">Login</label>
	  <input required="required" id="login" type="text" name="login" placeholder="Saisir le login" value="${formateur.login}">
	
	  <label for="password">Password</label>
	  <input required="required" id="password" type="password" name="password" placeholder="Saisir le password" value="${formateur.password}">
	
	  <label for="nom">Nom</label>
	  <input required="required" id="nom" type="text" name="nom" placeholder="Saisir le nom" value="${formateur.nom}">
	
	  <label for="prenom">Prenom</label>
	  <input required="required" id="prenom" type="text" name="prenom" placeholder="Saisir le prenom" value="${formateur.prenom}">
	
	  <label>Civilite</label>
	  <div class="choice-group">
	    <c:forEach items="${civilites}" var="civ">
	      <div class="choice-item">
	        <c:choose>
	          <c:when test="${formateur.civilite==civ}">
	            <input type="radio" checked id="civilite-${civ}" name="civilite" value="${civ}">
	            <label for="civilite-${civ}">${civ}</label>
	          </c:when>
	          <c:otherwise>
	            <input type="radio" id="civilite-${civ}" name="civilite" value="${civ}">
	            <label for="civilite-${civ}">${civ}</label>
	          </c:otherwise>
	        </c:choose>
	      </div>
	    </c:forEach>
	  </div>
	
	  <label>Administration</label>
	  <div class="choice-group">
	    <div class="choice-item">
	      <input id="admin" type="checkbox" name="admin" ${(formateur.admin) ? "checked" : ""}>
	      <label for="admin">Est Admin</label>
	    </div>
	  </div>
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="formateur" class="btn btn-primary">Annuler</a>
	  </div>
	</form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>