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


			<form action="ChooseProducts" method="post">
				
				<c:forEach items="${productos}" var="producto">
					<label for="nombre">${producto.getNombreProducto()}</label>
					<label for="descripcion">${producto.getDescripcionProducto()}</label>
					<label for="precio">${producto.getPrecioProducto()}</label>
					<label for="stock">${producto.getStockProducto()}</label>
				
					<input type="number" id="tockToSel${producto.getIdProducto()}" name="tockToSel${producto.getIdProducto()}" min="0" max="${producto.getStockProducto()}">  
					<br>
				</c:forEach>
				<input type="submit" name="DatosPedido" value="buy"/>
			</form>
			<br>
			

</body>
</html>