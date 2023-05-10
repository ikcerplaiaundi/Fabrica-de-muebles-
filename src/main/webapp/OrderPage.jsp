<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.DAO.Pedido" %>
<%@ page import="modelo.DAO.Client" %>
 <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="OrderPage" method="post">

		<c:forEach items="${pedidos}" var="pedido">
			<lable for="Id">Id pedido </lable>
			<input type="text" id="Id" value="${pedido.getIdPedido()}">
			<label for="Costo">Coste pedido </label>
			<input type="text" id="Costo" value="${pedido.getCosto()}">
			<lable for="Idcliente">Id cliente </lable>
			<input type="text" id="Idcliente" value="${pedido.client.getIdClient()}">
			<lable for="DireccionClient">Direccion cliente</lable>
			<input type="text" id="DireccionClient" value="${pedido.client.getDireccionClient()}">
			<label for="factura">Id factura</label>
			<input type="text" id="factura" value="${pedido.getIdFactura()}">
			<label for="fecha">Fecha pedido</label>
			<input type="text" id="fecha" value="${pedido.getFechaPedido()}">
			
			<button type="button" class="btn btn-primary btn-sm">Modificar</button><br/>
		</c:forEach>
	</form>

</body>
</html>