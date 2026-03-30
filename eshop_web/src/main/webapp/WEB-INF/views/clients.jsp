<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des clients</title>
</head>
<body>
<content>
	<table>
		<tr>
			<th>Id</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Civilite</th>
			<th>Date Naissance</th>
			<th>Adresse</th>
			<th>Actions</th>
		</tr>
		<c:if test="${clients.isEmpty()}"><tr><td align="center" colspan="7">AUCUN CLIENT</td></tr></c:if>
		
		<c:forEach items="${clients}" var="client">
			<tr>
				<td>${client.id}</td>
				<td>${client.nom}</td>
				<td>${client.prenom}</td>
				<td>${client.civilite}</td>
				<td>${client.dateNaissance}</td>
				<td>${client.adresse.numero} ${client.adresse.voie}, ${client.adresse.ville} ${client.adresse.cp}</td>
				<td>
					<a class="btn btn-warning" href="client/${client.id}">Modifier</a>
					<a class="btn btn-danger" href="client/delete/${client.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${client.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
		<c:set var="chemin" value="client"/>
	</c:if>
	
	<c:if test="${client.id!=null}">
		<div class="message-form">Formulaire d'update (Client ${client.id})</div>
		<c:set var="chemin" value="client/${client.id}"/>
	</c:if>
	
	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="client">
	  <form:hidden path="id"/>
	
	  <label for="nom">Nom</label>
	  <form:input required="required" type="text" path="nom" placeholder="Saisir le nom"/>
	 
	   <label for="prenom">Prenom</label>
	  <form:input required="required" type="text" path="prenom" placeholder="Saisir le prenom"/>
	  
	  
	 
	  	<label>Civilite</label>
		<div class="choice-group">
			<form:radiobuttons required="required" items="${civilites}"  path="civilite"/>
		</div>
	  
	    <label for="dateNaissance">Date de naissance</label>
	  <form:input required="required" type="date" path="dateNaissance"/>
	  
	  <h2>Adresse</h2>
	  <form:label path="adresse.numero">Numero</form:label>
	  <form:input required="required" type="text" path="adresse.numero" placeholder="Saisir le numero" />
	  
	   <form:label path="adresse.voie">Voie</form:label>
	  <form:input required="required" type="text" path="adresse.voie" placeholder="Saisir la voie" />
	  
	   <form:label path="adresse.ville">Ville</form:label>
	  <form:input required="required"  type="text" path="adresse.ville" placeholder="Saisir la ville" />
	  
	   <form:label path="adresse.cp">CP</form:label>
	  <form:input required="required" type="text" path="adresse.cp" placeholder="Saisir le cp" />
	 
	  <div class="form-actions">
	    <form:button class="btn btn-success">Sauvegarder</form:button>
	    <a href="client" class="btn btn-primary">Annuler</a>
	  </div>
	</form:form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>