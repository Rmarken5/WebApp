<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.model.User" %>
<%
User user = null;
if(session.getAttribute("user") != null){
 user = (User)session.getAttribute("user");
}
%>

<html>
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
function changeBackground(element){
	var randomColor = Math.floor(Math.random()*16777215).toString(16);
	//element.style.backgroundColor = randomColor;
	element.style.className = "logo";

}
  
</script>
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

	  ga('create', 'UA-103584392-1', 'auto');
	  ga('send', 'pageview');

	</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">


</head>
