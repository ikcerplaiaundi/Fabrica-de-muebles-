<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="modelo.DAO.Client" %>
 <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="ClientManagement" method="post">
	
		<c:forEach items="${clientes}" var="cliente">
			<label for="Id">${cliente.getIdClient()}</label>
			<label for="Costo">${cliente.getDireccionClient()}</label>
			<label for="fecha">${cliente.getNombreClient()}</label>
			<label for="fecha">${cliente.getContactoClient()}</label>
			<label for="fecha">${cliente.getDniClient()}</label>
			<label for="fecha">${cliente.getRegistrado()}</label><br/>
		</c:forEach>
	</form>
</body>
</html>