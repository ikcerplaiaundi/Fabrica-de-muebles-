<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="OrderPage" method="post">

<h1>ESTAS?</h1>

	<c:forEach items="${pedidos}" var="pedidos">
	
	<p>${pedidos.getIDPedido)(}</p><br/>
	<p>${pedidos.getFechaPedido()}</p><br/>
	<p>${pedidos.getCosto()}</p><br/>
	<p>${pedidos.getIdFactura()}</p><br/>
	</c:forEach>
</form>

</body>
</html>