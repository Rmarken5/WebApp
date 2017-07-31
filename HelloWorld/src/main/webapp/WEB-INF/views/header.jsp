

	
	<form:form action="direct" method="POST" >
		<div id = "bannerContainer">
		<!-- Rectangle -->
			<div class = "rectangle">
				<ul id = "banner">
					<li><a href="${contextPath}" class="logo">Valley Jam</a></li>
					
						<li onmouseover="changeBackground(this)" id ="main"><a href = "${contextPath}/home">Main</a></li>
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
	 