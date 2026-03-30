<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Site en construction (Espace Formateur)</h1>

<h2>Bienvenue ${connected.login}</h2>
<form action="formateur/identifiants" method="post" class="form-clean">
	  <label for="login">Login</label>
	  <input required="required" type="text" name="login" placeholder="Saisir le login"/>
	  <input required="required" type="password" name="password" placeholder="Saisir le password"/>
	  <div class="form-actions">
	    <input type="submit" value="Changer identifiants" class="btn btn-success">
	  </div>
</form>

</body>
</html>