<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>YouTube Videos</title>
</head>
<body>
hiiii
<%HttpSession sess=request.getSession(false);
String str=sess.getAttribute("res").toString();
System.out.println(str);
%>
<%-- <% SearchResult searchResult = new SearchResult();
//creating arraylist object of type category class
ArrayList<SearchResult> list = ArrayList<SearchResult>();
//storing passed value from jsp
list = request.getAttribute("list");
for(int i = 0; i < list.size(); i++) {
	SearchResult = list.get(i);
out.println( SearchResult.getId());
}
%> --%>
</body>
</html>