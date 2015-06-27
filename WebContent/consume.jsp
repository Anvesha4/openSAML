<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "openSAML.code.*" %>
<%@ page import = "java.util.HashSet" %>
<%@ page import = "java.util.HashMap" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	AuthResponse samlResponse = new AuthResponse();
	HashMap<String, HashSet<String>> claimsContainer = samlResponse.processResponse(request.getParameter("SAMLResponse"));
	
	for (String claim : claimsContainer.keySet()) {
		//System.out.print(" ");
		System.out.print(claim.toString() +" : ");
		HashSet<String> claimValues = claimsContainer.get(claim);
		for (String claimValue : claimValues) {
			System.out.print(claimValue + " ");
		}
		System.out.println();
	}
	
%>
</body>
</html>