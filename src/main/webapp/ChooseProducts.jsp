<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.DAO.Client"%>
 <%@page import="java.util.*" session="true" %>
 <%@page import="javax.servlet.http.HttpSession"%>
 <%@page import="modelo.DAO.Producto"%>
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


			<form action="Principal" method="post">
				<%
				ArrayList <Producto> productos= (ArrayList<Producto>)request.getAttribute("productos");
				for (Producto producto : productos) {
					
				%>
				<label for="nombre"><%= producto.getNombreProducto() %></label>
				<label for="descripcion"><%= producto.getDescripcionProducto() %></label>
				<label for="precio"><%= producto.getPrecioProducto() %></label>
				<label for="stock"><%= producto.getStockProducto() %></label>
			
				<input type="number" id="tockToSel" name="tentacles<%=producto.getIdProducto()%>" min="0" max="<%=producto.getStockProducto()%>">  
				
			
			
				<label for="name">El name es(${usuario.getNombre()}) </label> 
				
				<% } %>
				<input type="submit" name="Datos de pedido" value="buy"/>
			</form>
			<br>
			

</body>
</html>