<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion des filieres</title>
</head>
<body>

<table border>

<tr><th>Id</th><th>Libelle</th><th>Date Debut</th><th>Date Fin</th><th>Actions</th></tr>
<tr><td>${filieres[0].id}</td><td>${filieres[0].libelle}</td><td>${filieres[0].debut}</td><td>${filieres[0].fin}</td><td><a href="filiere?id=${filieres[0].id}"><input type="button" value="Modifier"></a></td></tr>
<tr><td>${filieres[1].id}</td><td>${filieres[1].libelle}</td><td>${filieres[1].debut}</td><td>${filieres[1].fin}</td><td><a href="filiere?id=${filieres[1].id}"><input type="button" value="Modifier"></td></tr>
</table>



<p>Pour le futur formulaire d'update : ${filiere}</p>
</body>
</html>