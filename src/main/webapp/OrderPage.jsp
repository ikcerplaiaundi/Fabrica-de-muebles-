<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.DAO.Pedido" %>
<%@ page import="modelo.DAO.Client" %>
 <%@page import="modelo.DAO.Empleado"%>
 <%@page import="java.util.*" session="true" %>
 <%@page import="javax.servlet.http.HttpSession"%>
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
<link rel="stylesheet"  href="style/SupplierStyle.css">
<title>Insert title here</title>
</head>
<body>

<%
Empleado logedEmpleado =new Empleado();
logedEmpleado = (Empleado) session.getAttribute("logedEmpleado");
if(logedEmpleado != null){ %>
<header class="col-md-12">
<img src="style/logo1.webp" alt="Flowers in Chania">
<p>
<% out.print(logedEmpleado.getNombreEmpleado());%> 
</p>
</header>
	
	<div class="Pedidos">
	<h1>Pedidos</h1>
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
	</div>
	<h1>Crear un pedido nuevo</h1>
	<div class="PedidoNuevo">
		<form action="CreateOrder" method="post">
			
			<label for="Idpedido">ID pedido</label>
			<input type="text" id="Idpedido" name="Idpedido">
			
			<label for="Idcliente">ID cliente</label>
			<input type="text" id="Idcliente" name="Idcliente">
			
			<label for="Idfactura">ID factura</label>
			<input type="text" id="Idfactura" name="Idfactura">
			
			<lable for="Costo">Coste de pedido</lable>
			<input type="text" id="Costo" name="Costo">
			
			<lable for="fecha">Fecha pedido</lable>
			<input type="date" id="fecha" name="fecha">
			
			<input type="submit" value="Crear pedido">
		</form>
	</div>
	
	
<%}else{ %>

<a href="LogDrive">login or create</a>

<%}%>

</body>
</html>