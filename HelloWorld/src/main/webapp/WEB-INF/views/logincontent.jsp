<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<title>Login</title>
<div class="content-paragraph">
<p>	
	<center>
	<h1>${message}</h1>
		
			<form:form commandName="user" method="post" action="login">
			<div class="login-table">
				<table width="400px">
					<tr>
						<td height="100px" class="login-input">User Name:</td>
						<td class="login-input"><form:input path="userName" /></td>
					</tr>
					<tr>
						<td height="30px" class="login-input">Password:</td>
						<td class="login-input"><form:password path="password" /></td>
					</tr>
					<!-- 	
				<tr>
					<td colspan="3" ></td>
				</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
				<tr class = "trSpace">
					<td colspan="3"></tr>
				</tr>	 -->

				</table>
				</br>
					</br>
						</br>
							</br>
				<ul style="list-style: none;" class="submit">
				
					<li><input  type="image"
						src="${contextPath}/images/button_login.png" name="action"
						value="Login!" /></li>
						
					<li><input  type="image"
						src="${contextPath}/images/button_create_account.png"
						name="action" value="Create Account" />
					
					</li>
				</ul>
				</br>
		</div>
		</form:form> 
			
	</center>
<p>
</div>