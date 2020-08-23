<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Edit New Product(Admin)</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3><b>Edit Product in the Prevention Kit</b></h3>
	<p></p>
	<form action="insertproduct">

	
			<label> Product Name :
			
				<input type="text" value=${prod.productName } name = "pname" required 
				/>
			</label>
		
			<br><br>
	
			<label> Product Description : 
			
				<input type="text" value=${prod.productDescription } name = "pdesc"/>
			</label>
		
			<br><br>
			
			<label> Product Cost : 
			
				<input type="number" value=${prod.cost } name = "pcost" maxlength ="6" />
			</label>
		
		<br>	<br>
		
	<button name="action" value="insertproduct">Edit</button>
	</form>
	
	
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>