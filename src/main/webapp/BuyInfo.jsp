<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.DAO.Client"%>
<%@page import="java.util.*" session="true" %>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="modelo.DAO.Producto"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<link rel="stylesheet"  href="style/ChooseProducts.css">
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
<header class="col-md-12">
<img src="style/iconousuario.webp" alt="Flowers in Chania">
<p>
<% out.print(logedClient.getNombreClient());%> 
</p>
</header>
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
					<label for="fecha">Fecha pedido</label>
					<input type="date" id="fecha" name="fecha" value="${pedido.getFechaPedido()}">
					<br>
				
				<input id="buy" type="submit" name="DatosPedido" value="confirmar compra"/>
			</form>

<h2>precio total</h2> <p><%=session.getAttribute("preciototal") %></p>

<% ArrayList<Producto> productosCompra = (ArrayList<Producto>) session.getAttribute("productosCompra"); %>	
	<h2>Producto a comprar</h2>
	<form>
	<c:forEach items="${productosCompra}" var="producto">
		<label for="nombre">${producto.getNombreProducto()}</label>
		
		<label for="precio">${producto.getPrecioProducto()}</label>
		<label for="stock">${producto.getStockProducto()}</label>
		<br>
	</c:forEach>
	
 </form>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous">
		
	</script>
</body>
</html>