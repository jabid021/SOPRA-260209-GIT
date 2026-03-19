<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<link rel="stylesheet" href="style.css">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<title>Gestion des filieres</title>
</head>
<body>

<content>
	<table class="table" >
	<tr><th>Id</th><th>Libelle2</th><th>Date Debut</th><th>Date Fin</th><th>Actions</th></tr>
	<tr>
		<td>${filieres[0].id}</td><td>${filieres[0].libelle}</td><td>${filieres[0].debut}</td><td>${filieres[0].fin}</td>
		<td>
			<a class="btn btn-warning" href="filiere?id=${filieres[0].id}">Modifier</a>
			<a class="btn btn-danger" href="filiere?id=${filieres[0].id}&delete">Supprimer</a>
		</td>
	</tr>
	
	<tr>
		<td>${filieres[1].id}</td><td>${filieres[1].libelle}</td><td>${filieres[1].debut}</td><td>${filieres[1].fin}</td>
		<td>
			<a class="btn btn-warning" href="filiere?id=${filieres[1].id}">Modifier</a>
			<a class="btn btn-danger" href="filiere?id=${filieres[1].id}&delete">Supprimer</a>
		</td>
	</tr>
	<tr>
		<td>${filieres[2].id}</td><td>${filieres[2].libelle}</td><td>${filieres[2].debut}</td><td>${filieres[2].fin}</td>
		<td>
			<a class="btn btn-warning" href="filiere?id=${filieres[2].id}">Modifier</a>
			<a class="btn btn-danger" href="filiere?id=${filieres[2].id}&delete">Supprimer</a>
		</td>
	</tr>
	</table>
	
	<h2>${messageForm}</h2>
	<form action="filiere"  method="post">
		<input type="hidden" name="id" value="${filiere.id}">
		<input type="text" name="libelle" placeholder="Saisir libelle" value="${filiere.libelle}">
		<input type="date" name="debut" value="${filiere.debut}">
		<input type="date" name="fin" value="${filiere.fin}">
		<input class="btn btn-success" type="submit" value="Sauvegarder">
		<a class="btn btn-info" href="filiere">Annuler</a>
	</form>
	
	<br><br>
	<a class="btn btn-info" href="index.jsp">Retour</a>
</content>
</body>
</html>