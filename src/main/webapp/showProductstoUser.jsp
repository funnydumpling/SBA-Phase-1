<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
       
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Listing All Products(user)</title>
</head>
<body>

	<jsp:include page ="header.jsp"/>
	
	<h2 style="color:olive"><i>Listing all Items in the Prevention Kit</i></h2>
	
	
	<c:choose>
		<c:when test="${prods==null || prods.isEmpty() }">
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
					
					<c:forEach items="${prods }" var="prod">
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
	<br> 
	<c:if test="${msg != null}">
			<p><strong>${msg }</strong>
		</c:if>
	<br>
		<form action="user?action=add">
		<button name="action" value="add">Add to Kit</button>
		</form>
		<br> 
		<form action="user?action=delete">
		<button name="action" value="delete">Delete from Kit</button>
		</form>
		<br> 
		<form action="user?action=showkit">
		<button name="action" value="showkit">Display Kit</button>
		</form>
	
	
	<jsp:include page ="footer.jsp"/>
</body>
</html>