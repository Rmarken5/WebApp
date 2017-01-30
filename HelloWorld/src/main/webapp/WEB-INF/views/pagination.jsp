<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
int pageNum = 0;
long maxPages = 0;
	if(null != session.getAttribute("pageNum") && (Integer)session.getAttribute("pageNum") >= 0 ){
		pageNum = (Integer)session.getAttribute("pageNum");
	}else{
		pageNum = 1;
	}
	if(null != session.getAttribute("maxPages") && (Long)session.getAttribute("maxPages") >= 0 ){
		maxPages = (Integer)session.getAttribute("maxPages");
	}else{
		maxPages = 1;
	}
%>
<jsp:include page="../resources/js/pagination.js"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div id = "pagination">
	<button onclick="previousSkip();" value="<--"> </button> 
	<button onclick="previous();" value="<--"> </button> 
	<label>Page <%=pageNum%> of <%=maxPages%></label>
	<button onclick="next();" value="<--"> </button> 
	<button onclick="nextSkip();" value="<--"> </button> 
	
	</div>
</body>
</html>