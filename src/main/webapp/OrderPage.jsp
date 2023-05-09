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

	<c:forEach items="${pedidos}" var="pedidos">
	
	<lable for="IDPedido">${pedidos.getIDPedido()}</lable>
	<lable for="FechaPedido">${pedidos.getFechaPedido()}</lable>
	<lable for="CostoPedido">${pedidos.getCosto()}</lable>
	<lable for="IDFactura">${pedidos.getIdFactura()}</lable>
	
	</c:forEach>
</form>

</body>
</html>