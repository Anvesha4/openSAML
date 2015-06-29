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
<%

	//window.open("index.jsp", null, "height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
/*  	AuthRequest samlAssertionObject = new AuthRequest();
	String samlAssertion = samlAssertionObject.samlWriter(); 
 	response.sendRedirect("https://colo-pm2.adx.isi.edu/adfs/ls/?SAMLRequest="+ new String(Base64.encodeBase64String(samlAssertion.getBytes()))); 
	 */
	 response.sendRedirect("https://colo-pm2.adx.isi.edu/adfs/ls/idpinitiatedsignon.aspx?loginToRp=https://localhost:8443/openSAML/consume.jsp"); 
%>
</body>
</html>