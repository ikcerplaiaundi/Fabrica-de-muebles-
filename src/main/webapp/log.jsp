<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.util.*" session="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<link rel="stylesheet"  href="style/logStyle.css">
<title>Insert title here</title>
</head>
<body>

	
		
		
			<form action="LogDrive" class="border border-1 rounded-3 border border-secondary" method="post">
			
			<label for="Mensage">${Mensage}</label><br>
				<h1>login</h1>
				<label for="name">name</label>
				<input type="text"  
				name="nombre" id="nombre" 
				value="nombre" onblur="if(this.value==""){this.value='nombre'";}"
				onfocus="if(this.value=='nombre'){this.value='';}"><br>
			
			
				<label for="password">password</label> 
				<input type="password"  
				name="contra" id="contra" 
				value="contraseņa"
				onblur="if(this.value==""){this.value='contraseņa'";}"
				onfocus="if(this.value=='contraseņa'){this.value='';}"><br>
				
				<input type="submit" name="log in" value="log in"/>
				
				
			</form>
			
		
		<form action="LogDrive" class="border border-1 rounded-3 border border-secondary" method="post">
				<h1>create acount</h1>
					<label for="newClient">datos de cliente:</label> <br>
					<label for="newNombreClient">Nombre</label>
					<input type="text" id="newname" name="newname" required value=""><br>
					<label for="newpassword">password</label> 
					<input type="newpassword"  name="newcontra" id="newcontra" value=""><br>
					<label for="ContactoClient">Contacto</label>
					<input type="text" id="newContact" name="newContact" required value=""><br>
					<label for="DniClient">DNI</label>
					<input type="text" id="newDNI" name="newDNI" required value=""><br>
					<label for="DireccionClient">Direccion</label>
					<input type="text" id="newDireccion" name="newDireccion" required value=""><br>
					  
					<br>
				
				<input type="submit" name="DatosPedido" value="crear cuenta"/>
			</form>
		
		
	
	
	
	


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous">
		
	</script>
</body>
</html>