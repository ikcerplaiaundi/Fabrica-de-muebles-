<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="modelo.DAO.Proveedor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*" session="true"%>
<%@page import="javax.servlet.http.HttpSession"%>
 <%@page import="modelo.DAO.Empleado"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Supplier page</title>
</head>
<body>

<%
Empleado logedEmpleado =new Empleado();
logedEmpleado = (Empleado) session.getAttribute("logedEmpleado");
if(logedEmpleado != null){ %>
<p>
<% out.print(logedEmpleado.getNombreEmpleado());%> 
</p>

	<h1>Proveedores</h1>
	
		<c:forEach items="${proveedores}" var="provedor">
			<form method="post" action="SupplierPage">
				<lable for="direccionprov">Direccion proveedor</lable>
				<input type="text" id="direccionprov" name="direccionprov" value="${provedor.getDireccion()}">
				
				<lable for="cifprov">CIF proveedor</lable>
				<input type="text" id="cifprov" name="cifprov" value="${provedor.getCif()}">
				
				<lable for="contactoprov">Contacto proveedor</lable>
				<input type="text" id="contactoprov" name="contactoprov" value="${provedor.getTelefono()}">
				
				<lable for="nombreprov">Nombre proveedor</lable>
				<input type="text" id="nombreprov" name="nombreprov" value="${provedor.getNombre()}">
	
				<lable for="idprov">ID proveedor</lable>
				<input type="text" id="idprov" name="idprov" value="${provedor.getIdProveedor()}">
				
				<input type="submit" value="Modify"><br/>
			</form>
		</c:forEach>

	<h1>Crear Proveedor</h1>
			
		<form action="SupplierCreate" method="post">
		
			<lable for="direccionprov">Direccion proveedor</lable>
			<input type="text" id="direccionprov" name="direccionprov">
			
			<lable for="cifprov">CIF proveedor</lable>
			<input type="text" id="cifprov" name="cifprov">
				
			<lable for="contactoprov">Contacto proveedor</lable>
			<input type="text" id="contactoprov" name="contactoprov">
			
			<lable for="nombreprov">Nombre proveedor</lable>
			<input type="text" id="nombreprov" name="nombreprov">
			
			<input type="submit" value="Añadir Proveedor">	
			</form>
	
<%}else{ %>

<a href="LogDrive">login or create</a>

<%}%>

</body>
</html>