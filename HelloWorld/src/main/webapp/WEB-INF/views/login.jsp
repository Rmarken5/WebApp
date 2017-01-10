<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
body{
background-color: maroon;
color : white;
}
table{
	border: 6px solid olive;
	background-color: #4CAF50;
  	color: white;
	border-collapse:collapse;
}
th, td {
    text-align: right;
}
input[type=text] {
    float: left;
    clear:both;
    overflow: hidden;
   
}
input[type=password] {
    float: left;
    clear:both;
	overflow: hidden;
}
.submit{

 margin-bottom: 0px;
 border:none;
 outline:none;
 width:100%;
 background-color: lime;
 height: 30px;
 }
 .trSpace{
 height:150px;
 text-align: center;
 color:white;
 
 }
 

</style>
<%@include file = "banner.jsp" %>
</head>
<body>
	<center>
	<h1>${message}</h1>
		<form:form commandName="user" method = "post" action = "login">
			<table  width = "400px"  >
				<tr>
					<td height = "100px" >User Name:</td>
					<td><form:input path="userName"/></td>
				</tr>
				<tr>
					<td height = "30px">Password:</td>
					<td><form:password path="password"/></td>	
				</tr>
				
				<tr>
					<td colspan="3" ><input class = "submit" type = "submit"  name ="action" value = "Login!"/></td>
				</tr>
				<tr>
					<td colspan="3"><input class = "submit"  type = "submit"  name ="action" value = "Create Account"/></td>
				</tr>
				<tr class = "trSpace">
					<td colspan="3"></tr>
				</tr>	
				
			</table>
			</form:form> 
			
	</center>
</body>
</html>