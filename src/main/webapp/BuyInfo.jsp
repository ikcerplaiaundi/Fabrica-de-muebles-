<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.DAO.Client"%>
<%@page import="java.util.*" session="true" %>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="modelo.DAO.Producto"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Client logedClient =new Client();
logedClient = (Client) session.getAttribute("logedClient");
if(logedClient != null){ %>
<p>
<% out.print(logedClient.getNombreClient());%> 
</p>
<%}else{ %>

<a href="LogDrive">login or create</a>

<%}%>
	
			<form action="BuyInfo" method="post">
					<label for="Client">datos de cliente:</label> <br>
					<label for="NombreClient">Nombre</label>
					<input type="text" id="name" name="name" required value="${logedClient.getNombreClient()}"><br>
					<label for="ContactoClient">Contacto</label>
					<input type="text" id="Contact" name="Contact" required value="${logedClient.getContactoClient()}"><br>
					<label for="DniClient">DNI</label>
					<input type="text" id="DNI" name="DNI" required value="${logedClient.getDniClient()}"><br>
					<label for="DireccionClient">Direccion</label>
					<input type="text" id="Direccion" name="Direccion" required value="${logedClient.getDireccionClient()}"><br>
					  
					<br>
				
				<input type="submit" name="DatosPedido" value="confirmar compra"/>
			</form>

<h2>precio total</h2> <p><%=session.getAttribute("preciototal") %></p>

<% ArrayList<Producto> productosCompra = (ArrayList<Producto>) session.getAttribute("productosCompra"); %>	
	<h2>Producto a comprar</h2>
	<c:forEach items="${productosCompra}" var="producto">
		<label for="nombre">${producto.getNombreProducto()}</label>
		<label for="descripcion">${producto.getDescripcionProducto()}</label>
		<label for="precio">${producto.getPrecioProducto()}</label>
		<label for="stock">${producto.getStockProducto()}</label>
		<br>
	</c:forEach>
	<br>
 
	
</body>
</html>