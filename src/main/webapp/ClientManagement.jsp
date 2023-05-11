<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="modelo.DAO.Client" %>
     <%@page import="modelo.DAO.Empleado"%>
 <%@page import="java.util.*" session="true" %>
 <%@page import="javax.servlet.http.HttpSession"%>
 <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="width: 100vw">

<%
Empleado logedEmpleado =new Empleado();
logedEmpleado = (Empleado) session.getAttribute("logedEmpleado");
if(logedEmpleado != null){ %>
<p>
<% out.print(logedEmpleado.getNombreEmpleado());%> 
</p>

	
		<c:forEach items="${clientes}" var="cliente">

			<form action="ClientManagement" method="post">
				
				<label for="Id">Id cliente</label>
				<input type="text" id="Id" name="Id" value="${cliente.getIdClient()}">
				
				<label for="direccioncliente">Direccion cliente</label>
				<input type="text" id="direccioncliente" name="direccioncliente" value="${cliente.getDireccionClient()}">
				
				<label for="nombreclient">Nombre cliente</label>
				<input type="text" id="nombreclient" name="nombreclient" value="${cliente.getNombreClient()}">
				
				<label for="contactclient">Contacto cliente</label>
				<input type="text" id="contactclient" name="contactclient" value="${cliente.getContactoClient()}">
				
				<label for="dniclient">Dni cliente</label>
				<input type="text" id="dniclient" name="dniclient" value="${cliente.getDniClient()}">
				
				
			</form>
		</c:forEach>
	
<%}else{ %>

<a href="LogDrive">login or create</a>

<%}%>
	
</body>
</html>