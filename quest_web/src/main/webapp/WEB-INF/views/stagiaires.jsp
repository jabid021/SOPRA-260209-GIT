<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des stagiaires</title>
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
			<th>Email</th>
			<th>Adresse</th>
			<th>Filiere</th>
			<th>Actions</th>
		</tr>
		<c:if test="${stagiaires.isEmpty()}"><tr><td align="center" colspan="10">AUCUN STAGIAIRE</td></tr></c:if>
		
		<c:forEach items="${stagiaires}" var="stagiaire">
			<tr>
				<td>${stagiaire.id}</td>
				<td>${stagiaire.login}</td>
				<td>${stagiaire.password}</td>
				<td>${stagiaire.nom}</td>
				<td>${stagiaire.prenom}</td>
				<td>${stagiaire.civilite}</td>
				<td>${stagiaire.email}</td>
				<td>${stagiaire.adresse.numero} ${stagiaire.adresse.voie}, ${stagiaire.adresse.ville} ${stagiaire.adresse.cp}</td>
				<td>${stagiaire.filiere.infoFiliere}</td>
				<td>
					<a class="btn btn-warning" href="stagiaire/${stagiaire.id}">Modifier</a>
					<a class="btn btn-danger" href="stagiaire/delete/${stagiaire.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${stagiaire.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
		<c:set var="chemin" value="stagiaire"/>
	</c:if>
	
	<c:if test="${stagiaire.id!=null}">
		<div class="message-form">Formulaire d'update (Stagiaire ${stagiaire.id})</div>
		<c:set var="chemin" value="stagiaire/${stagiaire.id}"/>
	</c:if>
	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="stagiaire">
	  <form:hidden path="id"/>

	  <label for="login">Login</label>
	  <form:input required="required" type="text" path="login" placeholder="Saisir le login"/>
	
	  <label for="password">Password</label>
	  <form:password required="required" path="password" placeholder="Saisir le password"/>
	  
	   <label for="nom">Nom</label>
	  <form:input required="required" type="text" path="nom" placeholder="Saisir le nom"/>
	  
	   <label for="prenom">Prenom</label>
	  <form:input required="required"  type="text" path="prenom" placeholder="Saisir le prenom" />
	  
	  
	  	<label>Civilite</label>
		<div class="choice-group">
			<form:radiobuttons required="required" items="${civilites}"  path="civilite"/>
		</div>
	  
	   <label for="email">Email</label>
	  <form:input required="required" type="email" path="email" placeholder="Saisir l'email" />
	  
	 
	  
	 
	  <h2>Adresse</h2>
	  <form:label path="adresse.numero">Numero</form:label>
	  <form:input required="required" type="text" path="adresse.numero" placeholder="Saisir le numero" />
	  
	   <form:label path="adresse.voie">Voie</form:label>
	  <form:input required="required" type="text" path="adresse.voie" placeholder="Saisir la voie" />
	  
	   <form:label path="adresse.ville">Ville</form:label>
	  <form:input required="required"  type="text" path="adresse.ville" placeholder="Saisir la ville" />
	  
	   <form:label path="adresse.cp">CP</form:label>
	  <form:input required="required" type="text" path="adresse.cp" placeholder="Saisir le cp" />
	  
	  <form:label path="filiere.id">Filiere</form:label>
	 
	 <form:select required="required" path="filiere.id">
		 <form:option value="">Choisir une filiere</form:option>
		 <form:options items="${filieres}" itemValue="id" itemLabel="infoFiliere"/>
	 </form:select>
	 
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="stagiaire" class="btn btn-primary">Annuler</a>
	  </div>
	</form:form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>