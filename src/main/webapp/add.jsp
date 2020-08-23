<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add Product to Kit(User)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h3>Add Product to your Kit</h3>
	
	<form action="user?action=addnewitem">
		
		<label>Product Id : <input type="number" name="id"></label>
		<label>Quantity   :<input type="number" name="quantity"></label>
		
		<button name="action" value="addnewitem">Proceed</button>
	
	</form>
	
	<br>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>