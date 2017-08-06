<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
<div class="content-paragraph">
	<p>
	<center>
		<h1>${message}</h1>
		<form:form commandName="newUser" method="post" action="create">
			<div class="login-table">
				<table width="400px" height="400px">
					<tr>
						<td class = "label">User Name:</td>
						<td class="login-input"><form:input path="userName" /></td>
					</tr>
					<tr>
						<td class = "label">E-Mail Address:</td>
						<td class="login-input"><form:input path="email" /></td>
					</tr>
					<tr>
						<td class = "label">First Name:</td>
						<td class="login-input"><form:input path="firstName" /></td>
					</tr>
					<tr> 
						<td class = "label">Last Name:</td>
						<td class="login-input"><form:input path="lastName" /></td>
					</tr>
					<tr>
						<td class = "label">Password:</td>
						<td class="login-input"><form:password path="password" /></td>
					</tr>
					<tr>
						<td class = "label">Re-enter Password:</td>
						<td class="login-input"><input type="password" id="rePass" name="rePass" /></td>
					</tr>
					
				</table>
				<ul style="list-style: none;" class="submit">
				<li>
				<input  type="image"
						src="${contextPath}/images/button_create_account.png"
						name="action" value="Create Account" />
						</li>
							</ul>
			</div>
		</form:form>

	</center>
	</p>
</div>