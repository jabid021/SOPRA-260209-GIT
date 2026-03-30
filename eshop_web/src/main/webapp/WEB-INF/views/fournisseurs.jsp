<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des fournisseurs</title>
</head>
<body>
<content>
	<table>
		<tr>
			<th>Id</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Civilite</th>
			<th>Societe</th>
			<th>Actions</th>
		</tr>
		<c:if test="${fournisseurs.isEmpty()}"><tr><td align="center" colspan="6">AUCUN FOURNISSEUR</td></tr></c:if>
		
		<c:forEach items="${fournisseurs}" var="fournisseur">
			<tr>
				<td>${fournisseur.id}</td>
				<td>${fournisseur.nom}</td>
				<td>${fournisseur.prenom}</td>
				<td>${fournisseur.civilite}</td>
				<td>${fournisseur.societe}</td>
				<td>
					<a class="btn btn-warning" href="fournisseur/${fournisseur.id}">Modifier</a>
					<a class="btn btn-danger" href="fournisseur/delete/${fournisseur.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${fournisseur.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
		<c:set var="chemin" value="fournisseur"/>
	</c:if>
	
	<c:if test="${fournisseur.id!=null}">
		<div class="message-form">Formulaire d'update (Fournisseur ${fournisseur.id})</div>
		<c:set var="chemin" value="fournisseur/${fournisseur.id}"/>
	</c:if>
	
	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="fournisseur">
	  <form:hidden path="id"/>
	
	  <label for="nom">Nom</label>
	  <form:input required="required" type="text" path="nom" placeholder="Saisir le nom"/>
	 
	   <label for="prenom">Prenom</label>
	  <form:input required="required" type="text" path="prenom" placeholder="Saisir le prenom"/>
	  
	  
	 
	  	<label>Civilite</label>
		<div class="choice-group">
			<form:radiobuttons required="required" items="${civilites}"  path="civilite"/>
		</div>
	  
	    <label for="societe">Societe</label>
	  <form:input required="required" type="text" path="societe"/>
	  
	  <div class="form-actions">
	    <form:button class="btn btn-success">Sauvegarder</form:button>
	    <a href="fournisseur" class="btn btn-primary">Annuler</a>
	  </div>
	</form:form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>