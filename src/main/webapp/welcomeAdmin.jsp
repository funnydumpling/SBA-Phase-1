<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h3><b>Welcome, Admin</b></h3>
	<p><c:if test="${msg != null}">
			<p><strong>${msg }</strong>
	</c:if>
		</p>
	
	<form action="list">
	<button name="action" value="list">View Products</button>
	</form>

	
	<br>

	<form action="newproduct">
	<button name="action" value="newproduct">Add New Item</button>
	</form>
	</span>
	
	<br>
	<form action="logout">
	<button name="action" value="logout">Log Out</button>
	</form>

	
	<br>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>