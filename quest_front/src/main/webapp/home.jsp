<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quest-front accueil</title>
</head>
<body>


<h1>App Quest-front</h1>

<form action="login" method="POST">
	<input type="text" name="login" placeholder="Saisir votre login">
	<input type="password" name="password" placeholder="Saisir votre password">
	<input type="submit" value="Se connecter">
	<c:if test="${error!=null}">
		<div class="error" style="color:red">Identifiants Invalide</div>
	</c:if>
</form>

</body>
</html>