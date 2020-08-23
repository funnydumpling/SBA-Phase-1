<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Order Summary(user)</title>
</head>
<body>
<jsp:include page ="header.jsp"/>
	
	<h2 style="color:indianred"><i>Order Summary</i></h2>
	
	<p>Hi <b>${order.getCoronaKit().getPersonName()}</b>, please find your order summary details as follows.</p>
	
	<br>
		
  	<table border="1" cellspacing="5px" cellpadding="5px" width="50%" style="text-align:center;">
		<tr>
			<th style="font-size:20px;">Product</th>
			<th style="font-size:20px;">Quantity</th>
			<th style="font-size:20px;">Cost</th>
		</tr>
		
		<c:forEach items="${order.getKitDetails()}" var="x">
			<tr>
			<td><c:out value="${x.getProductName() }" /></td>
			<td><c:out value="${x.getQuantity() }"/></td>
			<td>&#8377;<c:out value="${x.getAmount()}"/></td>
			</tr>
		</c:forEach>
		
		<c:set var="total" value="${0}"/>
		<c:forEach items="${order.getKitDetails()}" var="x">
		    <c:set var="total" value="${total + x.getAmount()}" />
		</c:forEach>
		
		<tr>
			<td colspan="2" style="font-size:20px"><b>Bill Amount</b></td>
			<td style="color:purple;font-size:20px"><b>&#8377;${total}</b></td>
			
		</tr>
		
	
		
	</table>
	<p><b><i><u>Delivery Address :</u></i></b></p>
	<span><c:forTokens items="${order.getCoronaKit().getDeliveryAddress() }" delims="," var="mySplit">
   	<c:out value="${mySplit}"/></br>
	</c:forTokens>
	</span>
	<p><b><i><u>Contact details :</u></i></b></p>
	<span>${order.getCoronaKit().getContactNumber() }</span>
	<br>
	<span>${order.getCoronaKit().getEmail() }</span>
	<jsp:include page ="footer.jsp"/>
</body>
</html>