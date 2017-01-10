<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="banner.jsp"%>
<%int i = 1; %>

<script  type="text/javascript" >
	$(document).ready(function(){

   $("td").addClass("images");
   $("img").addClass("image");
  // $(document).css('background-image', 'url( /HelloWorld/src/main/webapp/WEB-INF/resources/images/i89.jpg)');

});
</script>
<title>Valley Jam - Gallery Page</title>

<style type="text/css">
body {
	background-image: url("../resources/images/i89.jpg");
} 
</style>
</head>
<body>
	<form:form id="mainForm">
		<table id = "mainTable" style = "table-layout:auto;">
		<tr>
		<c:forEach items="${galleryImages}" var="image">
		 
		<% if(i % 5 == 0){%>
		</tr>
		 <tr>
		 <% } %>
			<td><img src="img/${image.getName()}"  alt="image"/> </td>
		<% if(i % 5 == 0){ %>
		 </tr>
		 <% } %>
		 <%i++; %>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>