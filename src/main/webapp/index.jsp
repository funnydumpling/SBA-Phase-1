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
	<form action="admin?action=login" method=POST>
	<h2 style="color:teal"><i>Welcome to Order Portal</i></h2>
	<p></p>
	<p>If you're an admin, enter your credentials in the form below.</p>
		<span>
			<label> Admin username : 
				<input type="text" id="loginid" name="loginid"/>
			</label>
		</span>
		
		<span>
			<label> Admin password : 
			
				<input type="password" id="password" name="password"/>
			</label>
		</span>
		
		<input type="submit" value="Login"/>
	</form>
	
	<jsp:include page ="footer.jsp"/>
</body>
</html>