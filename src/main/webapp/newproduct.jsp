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
<hr/>

<h3><b>${prod.id ==null?"New Product to the Prevention Kit":"Edit Product to the Prevention Kit"}</b></h3>
	<p></p>
	<form action="${prod.id ==0?"insertproduct":"updateProduct"}" method=POST>
			
			<label> Product ID<i>[auto-generated]</i> :
			
			<input type="text" value="${prod.id }" name= "id" readonly>
			</label>	
			
			<br> <br>
			<label> Product Name :
			
				<input type="text" value="${prod.productName }" name = "pname" required 
				/>
			</label>
		
			<br><br>
	
			<label> Product Description : 
			
				<input type="text" value="${prod.productDescription }" name = "pdesc" required/>
			</label>
		
			<br><br>
			
			<label> Product Cost : 
			
				<input type="number" value ="${prod.cost }" name = "pcost" maxlength ="6" required/>
			</label>
		
		<br>	<br>
		
	<button  name="action" value="${prod.id ==0?"insertproduct":"updateProduct"}">SAVE</button>	
	</form>
	
	
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>