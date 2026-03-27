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
	
	<form:form action="formateur${formateur.id == null ? '' : '/'.concat(formateur.id)}" method="post" class="form-clean" modelAttribute="formateur">
	  <form:hidden path="id"/>

	  <label for="login">Login</label>
	  <form:input required="required" type="text" path="login" placeholder="Saisir le login"/>
	
	  <label for="password">Password</label>
	  <form:input type="password" required="required" path="password" placeholder="Saisir le password"/>
	  
	   <label for="nom">Nom</label>
	  <form:input required="required" type="text" path="nom" placeholder="Saisir le nom"/>
	  
	   <label for="prenom">Prenom</label>
	  <form:input required="required"  type="text" path="prenom" placeholder="Saisir le prenom" />
	  
	  
	  	<label>Civilite</label>
		<div class="choice-group">
			<form:radiobuttons required="required" items="${civilites}"  path="civilite"/>
		</div>
	
	  <label>Administration</label>
	  <div class="choice-group">
	    <div class="choice-item">
	      <form:checkbox path="admin" label="Est Admin"/>
	    </div>
	  </div>
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="formateur" class="btn btn-primary">Annuler</a>
	  </div>
	</form:form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>