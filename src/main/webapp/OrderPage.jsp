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

	

		<c:forEach items="${pedidos}" var="pedido">
		<form method="post" action="OrderPage">
			<lable for="Id">Id pedido </lable>
			<input type="text" id="Id" name="Id" value="${pedido.getIdPedido()}">
			
			<label for="Costo">Coste pedido </label>
			<input type="text" id="Costo" name="Costo" value="${pedido.getCosto()}">
			
			<lable for="Idcliente">Id cliente </lable>
			<input type="text" id="Idcliente" name="Idcliente" value="${pedido.client.getIdClient()}">
			
			<lable for="DireccionClient">Direccion cliente</lable>
			<input type="text" id="DireccionClient" name="DireccionClient" value="${pedido.client.getDireccionClient()}">
			
			<label for="factura">Id factura</label>
			<input type="text" id="factura" name="factura" value="${pedido.getIdFactura()}">
			
			<label for="fecha">Fecha pedido</label>
			<input type="date" id="fecha" name="fecha" value="${pedido.getFechaPedido()}">
			
			<input type="submit" value="Modify"><br/>
		</form>
		</c:forEach>
	

</body>
</html>