<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page import="com.model.User" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
User user = null;
if(session.getAttribute("user") != null){
 user = (User)session.getAttribute("user");
}
%>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
function changeBackground(element){
	var randomColor = Math.floor(Math.random()*16777215).toString(16);
	//element.style.backgroundColor = randomColor;
	element.style.className = "logo";
	
}

</script>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<style>
.galleryImageTable{
width: 100%;	
table-layout: fixed;
}
/* .galleryImageRow{
height:150px;
width:100%;
padding: 15px;
} */
.galleryImageCell{
 height:20%; 
width:20%;
/*  padding: 15px;
border-sytle: onset black 5px; */

}

#banner li a {
        text-shadow: 0 2px 1px rgba(0,0,0,0.5);
        display: block;
        text-decoration: none;
        color: #0642a3;
        font-size: 1.6em;
        margin: 0 .5em;
}
#banner li a:hover {
        margin-top: 2px;
        color: red;
}
 #banner li {
	 list-style: none;
        display: block;
        float: left;
        margin: 1em;
        overflow: hidden;
} 
.rectangle {
   background: black;
   height: 62px;
   position: relative;
   -moz-box-shadow: 0px 0px 4px rgba(0,0,0,0.55);
   box-shadow: 0px 0px 4px rgba(0,0,0,0.55);
   -webkit-border-radius: 3px;
   -moz-border-radius: 3px;
   border-radius: 3px;
   z-index: 500; /* the stack order: foreground */
   margin: 3em 0;
}
.buttons{
	position:relative;
	list-style:none;
	background-color: black;
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    width: 80px;
    font-size: 16px;
    height: 100%;
    font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
    display: block;
   	float: left;
    margin: 1em;
}

.logo {	
	font-size: 35px;
	color: #808080;
	font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}
</style>

</head>
<body id = "content-area">
	
	<form:form action="direct" method="POST" >
		<div id = "bannerContainer">
		<!-- Rectangle -->
			<div class = "rectangle">
				<ul id = "banner">
					<li><a href="${contextPath}" class="logo">Valley Jam</a></li>
					
						<li onmouseover="changeBackground(this)" id ="main"><a href = "home">Main</a></li>
						<li><a href = "${contextPath}/acts">Acts</a></li>
						<li><a href = "${contextPath}/gallery/1">Gallery</a></li>
						<li><a href = "${contextPath}/contact">Contact</a></li>
					
					<%
						if(null != user && null != user.getFirstName()){
					%>
					<li><label style="color:white;" for= "userName">Hello, <%=user.getFirstName()%> </label></li>			
					<%}else{%>	
					<li><a href="${contextPath}/login" >Login!</a><li>
					<li><a href="${contextPath}/create">Sign Up!</a></li>
					<%}%>		
				</ul>
			</div>
		</div>
			
				
				
				
				
				
		
	</form:form>
	
</body>
</html>