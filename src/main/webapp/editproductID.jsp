<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h3>Enter the product ID:</h3>
	
	<form action="editproduct">
	<input type="number" name="id">
	<button name="action" value="editproduct">Proceed</button>
	</form>
	
	<br>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>