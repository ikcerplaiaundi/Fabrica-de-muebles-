<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="modelo.DAO.Client"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>
<% 
Client client =new Client();
client = (Client) session.getAttribute("logedClient");
out.print(client.getNombreClient());
%> 
</h1>
</body>
</html>