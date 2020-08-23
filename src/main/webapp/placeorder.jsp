<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Place Order(user)</title>
</head>
<body>
<jsp:include page ="header.jsp"/>
	
	<h2 style="color:indianred"><i>Almost there..</i></h2>
	
	<p><b><u>Enter your address before proceeding with checkout.</u></p>
	
	
	<br>
		
  	<form action="user?action=saveorder">
			
			<label> Building</label>
            <input type="text" name="building" placeholder="234D - GreenView Apartment" required/>
            <br><br>         
       
            <label> Street/Locality</label>
            <input type="text" name="street" placeholder="12th Main,ABC Layout" required/>
            <br><br>         
       
            <label> City</label>
            <input type="text" name="city" placeholder="Bengaluru" required/>
            <br><br>         
       
            <label> Country</label>
            <input type="text" name="country" placeholder="India" required/>
            <br><br>         
       
            <label>Zipcode</label>
            <input type="number" name="zipcode" placeholder="575345" required/>
           
		<br><br>         
       
        <button name="action" value="saveorder">Continue to Checkout</button>
      </form>
	
	<jsp:include page ="footer.jsp"/>
</body>
</html>