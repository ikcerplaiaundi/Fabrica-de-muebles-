<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.DAO.Pedido" %>
 <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="OrderPage" method="post">

		<c:forEach items="${pedidos}" var="pedido">
			<label for="Id">${pedido.getIdPedido()}</label><br/>
			<label for="Costo">${pedido.getCosto()}</label><br/>
			<label for="factura">${pedido.getIdFactura()}</label><br/>
			<label for="fecha">${pedido.getFechaPedido()}</label><br/>
		</c:forEach>
	</form>

</body>
</html>