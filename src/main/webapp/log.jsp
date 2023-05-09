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
<title>Insert title here</title>
</head>
<body>
<h1>formulario</h1>
	
		
		
			<form action="LogDrive" method="post">
			<label for="Mensage">${Mensage}</label><br>
			
				<label for="name">name</label>
				<input type="text"  
				name="nombre" id="nombre" 
				value="nombre" onblur="if(this.value==""){this.value='nombre'";}"
				onfocus="if(this.value=='nombre'){this.value='';}"><br>
			
			
				<label for="password">password</label> 
				<input type="text"  
				name="contra" id="contra" 
				value="contraseña"
				onblur="if(this.value==""){this.value='contraseña'";}"
				onfocus="if(this.value=='contraseña'){this.value='';}"><br>
				
				<input type="submit" name="sumit" value="sumit"/>
				
				
			</form>
			<br>
		
		
		
		
	
	
	
	


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous">
		
	</script>
</body>
</html>