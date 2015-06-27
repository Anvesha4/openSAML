<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="openSAML.code.*" %>  <%@ page import="org.apache.commons.codec.binary.Base64" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 	<h1>Login Page</h1> 
	<center> <h2>Signup Details</h2> 
		<form action="/" method="post"> 
			<br/>Username:<input type="text" name="username"> 
			<br/>Password:<input type="password" name="password"> 
			<br/>
			<input type="submit" value="Submit"> 
		</form> 
	</center> -->
<%

	//window.open("index.jsp", null, "height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
 	/* AuthRequest samlAssertionObject = new AuthRequest();
	String samlAssertion = samlAssertionObject.samlWriter(); 
 	response.sendRedirect("https://colo-pm2.adx.isi.edu/adfs/ls/?SAMLRequest="+ new String(Base64.encodeBase64String(samlAssertion.getBytes()))); 
	 */
	 response.sendRedirect("https://colo-pm2.adx.isi.edu/adfs/ls/IdpInitiatedSignon.aspx"); 
%>
</body>
</html>