<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="modelo.DAO.Empleado"%>
 <%@page import="java.util.*" session="true" %>
 <%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
<link rel="stylesheet"  href="style/Style.css">
<title>Empresa muebles</title>
</head>
<body>


<%
Empleado logedEmpleado =new Empleado();
logedEmpleado = (Empleado) session.getAttribute("logedEmpleado");
if(logedEmpleado != null){ %>
<p>
<% out.print(logedEmpleado.getNombreEmpleado());%> 
</p>
<div class="dropdown" id="menu-desplegable">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Seleciona un rol
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<li><a class="dropdown-item" href="ManagerPage">Gestor</a></li>
			<li><a class="dropdown-item" href="ChooseProducts">Cliente</a></li>
		</ul>
	</div>
<%}else{ %>

<a href="LogDrive">login or create</a>

<%}%>

</body>
</html>