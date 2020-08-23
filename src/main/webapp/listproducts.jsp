<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit- List All Products(Admin)</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	
	<h3><b style="color:olive">Corona Prevention Kit</b></h3>
	<p></p>
	
	<c:choose>
		<c:when test="${products==null || products.isEmpty() }">
			<p>No Items Found</p>
		</c:when>
		<c:otherwise>
		<table border="1" cellspacing="5px" cellpadding="5px" width="70%" style="text-align:center;">
			<tr>
				<th>Product ID</th>
				<th>Product Name</th>
				<th>Product Description</th>
				<th>Product Cost</th>
				
			</tr>
			
			<c:forEach items="${products }" var="prod">
				<tr>
				<td>${prod.id }</td>
				<td>${prod.productName }</td>
				<td>${prod.productDescription }</td>
				<td>${prod.cost }</td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	<form action="delete">
	<button name="action" value="delete">Delete Item</button>
	</form>
	
	<form action="edit">
	<button name="action" value="edit">Edit Item</button>
	</form>
	
	<form action="goback">
	<button name="action" value="goback">Previous Page</button>
	</form>
	
	
	<jsp:include page="footer.jsp"/>
</body>
</html>