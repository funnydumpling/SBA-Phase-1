<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	
	<h3><b style="color:olive">Corona Prevention Kit</b></h3>
	<p></p>
	
	<c:choose>
		<c:when test="${kit==null || kit.isEmpty() }">
			<p>Your Kit is currently Empty</p>
		</c:when>
		<c:otherwise>
		<table border="1" cellspacing="5px" cellpadding="5px" width="50%" style="text-align:center;">
			<tr>
				<th>Product Id</th>
				<th>Quantity</th>
				<th>Total</th>
				
			</tr>
			
			<c:forEach items="${kit }" var="kit">
				<tr>
				<td>${kit.productName }</td>
				<td>${kit.quantity }</td>
				<td>${kit.amount }</td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	<br>
	<form action="showProductstoUser.jsp">
	<button>Previous Page</button>
	</form>
	
	<br>
	<c:if test="${kit!=null }">	
	<form action="user?action=placeorder">
	<button name="action" value="placeorder">Proceed with Order</button>
	</form>
	</c:if>
	<jsp:include page="footer.jsp"/>
</body>
</html>