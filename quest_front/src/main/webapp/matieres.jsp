<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des matieres</title>
</head>
<body>

<table border>

<tr><th>Id</th><th>Libelle</th><th>Actions</th></tr>
<tr><td>${matieres[0].id}</td><td>${matieres[0].libelle}</td><td><a href="matiere?id=${matieres[0].id}"><input type="button" value="Modifier"></a></td></tr>
<tr><td>${matieres[1].id}</td><td>${matieres[1].libelle}</td><td><a href="matiere?id=${matieres[1].id}"><input type="button" value="Modifier"></td></tr>
</table>



<p>Pour le futur formulaire d'update : ${matiere}</p>


<a href="index.jsp">Retour</a>
</body>
</html>