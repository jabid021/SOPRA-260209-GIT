<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Calculette - Carre</h1>

<% 
if(request.getMethod().equals("GET")){
//un comm que le nav ne voit JAMAIS
int nb = Integer.parseInt(request.getParameter("nombre"));
out.println("<p>Le carre de "+nb+" est "+(nb*nb)+"</p>");
}
else
{
	out.println("On est en post..");	
}
%>

</body>
</html>