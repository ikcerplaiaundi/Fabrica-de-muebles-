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

	<h1>Registrados</h1>
		<c:forEach items="${clientesregistrados}" var="clientereg">

			<form action="ClientManagement" method="post">
				
				<label for="Id">Id cliente</label>
				<input type="text" id="Id" name="Id" value="${clientereg.getIdClient()}">
				
				<label for="direccioncliente">Direccion cliente</label>
				<input type="text" id="direccioncliente" name="direccionclient" value="${clientereg.getDireccionClient()}">
				
				<label for="nombreclient">Nombre cliente</label>
				<input type="text" id="nombreclient" name="nombreclient" value="${clientereg.getNombreClient()}">
				
				<label for="contactclient">Contacto cliente</label>
				<input type="text" id="contactclient" name="contactclient" value="${clientereg.getContactoClient()}">
				
				<label for="dniclient">Dni cliente</label>
				<input type="text" id="dniclient" name="dniclient" value="${clientereg.getDniClient()}">
				
				<input type="submit" value="Modify"><br/>
			</form>
		</c:forEach>
	<h1>No registrados</h1>
		<c:forEach items="${clientesnoregistrados}" var="clientenoreg">

			<form action="ClientManagement" method="post">
				
				<label for="Id">Id cliente</label>
				<input type="text" id="Id" name="Id" value="${clientenoreg.getIdClient()}">
				
				<label for="direccioncliente">Direccion cliente</label>
				<input type="text" id="direccioncliente" name="direccionclient" value="${clientenoreg.getDireccionClient()}">
				
				<label for="nombreclient">Nombre cliente</label>
				<input type="text" id="nombreclient" name="nombreclient" value="${clientenoreg.getNombreClient()}">
				
				<label for="contactclient">Contacto cliente</label>
				<input type="text" id="contactclient" name="contactclient" value="${clientenoreg.getContactoClient()}">
				
				<label for="dniclient">Dni cliente</label>
				<input type="text" id="dniclient" name="dniclient" value="${clientenoreg.getDniClient()}">
				
				<input type="submit" value="Modify"><br/>
			</form>
		</c:forEach>	
	
	<h1>Crear nuevo user</h1>
	
		<form action="CreateClient" method="post">
		
		<label for="direccioncliente">Direccion cliente</label>
		<input type="text" id="direccioncliente" name="direccioncliente">
		
		<label for="nombreclient">Nombre Cliente</label>
		<input type="text" id="nombreclient" name="nombreclient">
		
		<label for="contactclient">Contacto Cliente</label>
		<input type="text" id="contactclient" name="contactclient">
		
		<label for="dniclient">DNI Cliente</label>
		<input type="text" id="dniclient" name="dniclient">
		
		<input type="submit" value="Crear">
		</form>
	
<%}else{ %>

<a href="LogDrive">login or create</a>

<%}%>
	
</body>
</html>