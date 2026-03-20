<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des stagiaires</title>
</head>
<body>

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
				<td>${stagiaire.filiere.id} - ${stagiaire.filiere.libelle}</td>
				<td>
					<a class="btn btn-warning" href="stagiaire?id=${stagiaire.id}">Modifier</a>
					<a class="btn btn-danger" href="stagiaire?id=${stagiaire.id}&delete">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${stagiaire.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
	</c:if>
	
	<c:if test="${stagiaire.id!=null}">
		<div class="message-form">Formulaire d'update (Stagiaire ${stagiaire.id})</div>
	</c:if>
	
	<form action="stagiaire" method="post" class="form-clean">
	  <input type="hidden" name="id" value="${stagiaire.id}">
	
	  <label for="login">Login</label>
	  <input required="required" id="login" type="text" name="login" placeholder="Saisir le login" value="${stagiaire.login}">
	
	  <label for="password">Password</label>
	  <input required="required" id="password" type="password" name="password" placeholder="Saisir le password" value="${stagiaire.password}">
	  
	   <label for="nom">Nom</label>
	  <input required="required" id="nom" type="text" name="nom" placeholder="Saisir le nom" value="${stagiaire.nom}">
	  
	   <label for="prenom">Prenom</label>
	  <input required="required" id="prenom" type="text" name="prenom" placeholder="Saisir le prenom" value="${stagiaire.prenom}">
	  
	  
	  	Civilite
	 	<c:forEach items="${civilites}" var="civ">
	 	 <c:choose>
	 	 	<c:when test="${stagiaire.civilite==civ}"><input type="radio" checked id="civilite-${civ}" name="civilite" value="${civ}"> <label for="civilite-${civ}">${civ}</label></c:when>
	 		<c:otherwise><input type="radio" id="civilite-${civ}" name="civilite" value="${civ}"> <label for="civilite-${civ}">${civ}</label></c:otherwise>
	 	 </c:choose>
	 	</c:forEach>
	  
	   <label for="email">Email</label>
	  <input required="required" id="email" type="email" name="email" placeholder="Saisir l'email" value="${stagiaire.email}">
	  
	 
	  
	 
	  <h2>Adresse</h2>
	  <label for="numero">Numero</label>
	  <input required="required" id="numero" type="text" name="adresse.numero" placeholder="Saisir le numero" value="${stagiaire.adresse.numero}">
	  
	   <label for="voie">Voie</label>
	  <input required="required" id="voie" type="text" name="adresse.voie" placeholder="Saisir la voie" value="${stagiaire.adresse.voie}">
	  
	   <label for="ville">Ville</label>
	  <input required="required" id="ville" type="text" name="adresse.ville" placeholder="Saisir la ville" value="${stagiaire.adresse.ville}">
	  
	   <label for="cp">CP</label>
	  <input required="required" id="cp" type="text" name="adresse.cp" placeholder="Saisir le cp" value="${stagiaire.adresse.cp}">
	  
	  <label for="filiere">Filiere</label>
	  <select id="filiere" required="required" name="filiere.id">
	 	  <option value="">Choisir une filiere</option>
	 	  <c:forEach items="${filieres}" var="filiere">
	 	  	<c:choose>
		 	  	<c:when test="${filiere.id==stagiaire.filiere.id}"> <option selected value="${filiere.id}">Filiere ${filiere.id} - ${filiere.libelle}</option></c:when>
		 	  	<c:otherwise><option value="${filiere.id}">Filiere ${filiere.id} - ${filiere.libelle}</option></c:otherwise>
	 	  	</c:choose>
	 	  </c:forEach>
	  </select>
	 
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="stagiaire" class="btn btn-primary">Annuler</a>
	  </div>
	</form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>