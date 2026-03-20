<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

<link rel="stylesheet" href="style.css">

<title>Gestion des matieres</title>
</head>
<body>

<content>
	<table>
		<tr>
			<th>Id</th>
			<th>Libelle</th>
			<th>Actions</th>
		</tr>
		<tr>
			<td>${matieres[0].id}</td>
			<td>${matieres[0].libelle}</td>
			<td>
				<a class="btn btn-warning" href="matiere?id=${matieres[0].id}">Modifier</a>
				<a class="btn btn-danger" href="matiere?id=${matieres[0].id}&delete">Supprimer</a>
			</td>
		</tr>
		
		<tr>
			<td>${matieres[1].id}</td><td>${matieres[1].libelle}</td>
			<td>
				<a class="btn btn-warning" href="matiere?id=${matieres[1].id}">Modifier</a>
				<a class="btn btn-danger" href="matiere?id=${matieres[1].id}&delete">Supprimer</a>
			</td>
		</tr>
		<tr>
			<td>${matieres[2].id}</td><td>${matieres[2].libelle}</td>
			<td>
				<a class="btn btn-warning" href="matiere?id=${matieres[2].id}">Modifier</a>
				<a class="btn btn-danger" href="matiere?id=${matieres[2].id}&delete">Supprimer</a>
			</td>
		</tr>
	</table>
	
	<div class="message-form">${messageForm}</div>
	
	<form action="matiere" method="post" class="form-clean">
	  <input type="hidden" name="id" value="${matiere.id}">
	  <input type="hidden" name="version" value="${matiere.version}">
	  <label for="libelle">Libellé</label>
	  <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir le libellé" value="${matiere.libelle}">
	
	  <div class="form-actions">
	    <input type="submit" value="Sauvegarder" class="btn btn-success">
	    <a href="matiere" class="btn btn-primary">Annuler</a>
	  </div>
	</form>
	
	<br><br>
	<a class="btn btn-info" href="index.jsp">Retour</a>
</content>
</body>
</html>