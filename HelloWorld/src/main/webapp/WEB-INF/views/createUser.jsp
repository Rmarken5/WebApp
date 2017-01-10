<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="banner.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>

<style type="text/css">
body{
background-color: maroon;
color : white;
}
table{
	border: 6px solid olive;
	background-color: #4CAF50;
  	color: white;

}
th, td {
	
    text-align: right;
}
input {
    float: left;
    clear:both;
}
button{
float: center;
}


</style>
</head>

<body>
	<center>
	<h1>${message}</h1>
		<form:form commandName="newUser" method = "post" action = "create">
			<table  width = "400px" height = "400px" >
				<tr>
				<td>User Name:</td>
				<td>
				<form:input path="userName"/>
				</td>
				</tr>
				<tr>
				<td>E-Mail Address:</td>
				<td>
				<form:input path="email"/>
				</td>
				</tr>
				<tr>
				<td>Password:</td>
				<td>
				<form:password path="password"/>
				</td>
				</tr>
				<tr>
				<td>Re-enter Password:</td><td>
					<input type = "password" id = "rePass" name = "rePass" />
				</td>
				</tr>
				<tr><td>
				<button id = "create">Create User!</button>
				</td>
				</tr>
			</table>
			</form:form> 
			
	</center>
</body>
</html>
</html>