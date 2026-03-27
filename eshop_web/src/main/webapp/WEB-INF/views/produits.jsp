<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des produits</title>
</head>
<body>
<content>
	<table>
		<tr>
			<th>Id</th>
			<th>Libelle</th>
			<th>Prix</th>
			<th>Fournisseur</th>
			<th>Actions</th>
		</tr>
		<c:if test="${produits.isEmpty()}"><tr><td align="center" colspan="5">AUCUN PRODUIT</td></tr></c:if>
		
		<c:forEach items="${produits}" var="produit">
			<tr>
				<td>${produit.id}</td>
				<td>${produit.libelle}</td>
				<td>${produit.prix}€</td>
				<td>Fournisseur ${produit.fournisseur.id} - ${produit.fournisseur.prenom} ${produit.fournisseur.nom}</td>
				<td>
					<a class="btn btn-warning" href="produit/${produit.id}">Modifier</a>
					<a class="btn btn-danger" href="produit/delete/${produit.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<c:if test="${produit.id==null}">
		<div class="message-form">Formulaire d'ajout</div>
		<c:set var="chemin" value="produit"/>
	</c:if>
	
	<c:if test="${produit.id!=null}">
		<div class="message-form">Formulaire d'update (Produit ${produit.id})</div>
		<c:set var="chemin" value="produit/${produit.id}"/>
	</c:if>
	
	<form:form action="produit" method="post" class="form-clean" modelAttribute="produit">
	  <form:hidden path="id"/>
	
	  <label for="libelle">Libelle</label>
	  <form:input required="required" type="text" path="libelle" placeholder="Saisir le libelle"/>
	  <label for="prix">Prix</label>
	  <form:input required="required" type="number" path="prix" min="0" step="0.01" />
	  
	 
	  <form:label path="fournisseur.id">Fournisseur</form:label>
	  <form:select required="required" path="fournisseur.id">
	 	  <form:option value="" label="Choisir un fournisseur"/>
	 	  <form:options items="${fournisseurs}" itemValue="id" itemLabel="infos"/>
	  </form:select>
	 
	
	  <div class="form-actions">
	    <form:button class="btn btn-success">Sauvegarder</form:button>
	    <a href="produit" class="btn btn-primary">Annuler</a>
	  </div>
	</form:form>
	
	<br><br>
	<a class="btn btn-info" href="home">Retour</a>
</content>
</body>
</html>