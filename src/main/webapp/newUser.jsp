<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Prevention Kit - Order Portal</title>
</head>
<body>
	
	<jsp:include page ="header.jsp"/>
	
	<h2 style="color:indianred"><i>New User Details Page</i></h2>
	
	<p><b>Enter your details below.</p>
		
	<form action="user?action=insertuser" method="POST">
	
		<label> ID :
		
			<input type="number" name = "id" required/>
		</label>
	
		<br><br>

		<label> Name : 
		
			<input type="text" name = "name" required/>
		</label>
	
		<br><br>
		
		<label> Email : 
		
			<input type="email" name = "email" required/>
		</label>
	
		<br><br>
		
		<label> Contact Number : 
		
			<input type="number" name = "contact" required/>
		</label>
	
		<br><br>
		
		<button name="action" value="user?action=insertuser">Submit</button>
	</form>
	
	<jsp:include page ="footer.jsp"/>
</body>
</html>