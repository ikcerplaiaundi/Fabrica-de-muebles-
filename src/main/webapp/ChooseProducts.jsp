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
<header class="col-md-12">
<a href="LogDrive">login or create</a>
</header>
<%}%>


			<form action="ChooseProducts" class="border border-1 rounded-1 border border-secondary" method="post">
					<label for="nombre">Nombre</label>
					<label for="descripcion">Descripcion</label>
					<label for="precio">Precio</label>
					<label for="stock">Stock</label>
				<c:forEach items="${productos}" var="producto">
					
					<br>
					<label for="nombre">${producto.getNombreProducto()}</label>
					<label for="descripcion">${producto.getDescripcionProducto()}</label>
					<label for="precio">${producto.getPrecioProducto()}</label>
					<label for="stock">${producto.getStockProducto()}</label>
				
					<input type="number" id="${producto.getIdProducto()}" name="${producto.getIdProducto()}"  value="" min="0" max="${producto.getStockProducto()}">  
					<br>
				</c:forEach>
				<input id="buy" type="submit" name="DatosPedido" value="buy"/>
			</form>
			<br>
			
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous">
		
	</script>
</body>
</html>