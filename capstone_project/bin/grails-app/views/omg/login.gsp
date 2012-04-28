<%@ page import="capstone_project.User" %>
<!doctype html>
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>Login</title>
	</head>
	<body>
		<div class="body">
		<h1>Login</h1>
		<g:if test="${flash.message}">
			<div class="message">${flash.message}</div>
		</g:if>
		<g:form action="authenticate" method="post" >
			<div class="dialog">
				<table style="width: 80%; align:center">
					<tbody>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="login">User Name:</label>
							</td>
							<td valign="top">
								<input type="text"
								id="login" name="login"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="password">Password:</label>
							</td>
							<td valign="top">
								<input type="password"
									id="password" name="password"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="buttons" align="center">
				<span class="button">
					<input type="submit" value="Login" />
				</span>
			</div>
		</g:form>
		<h1>Register</h1>
		<g:form action="createUser" method="post" >
			<div class="dialog">
				<table style="width: 80%; align:center">
					<tbody>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="login">User Name:</label>
							</td>
							<td valign="top">
								<input type="text"
								id="login" name="login"/>
							</td>
						</tr>
						<tr class="prop">
							<td valign="top" class="name">
								<label for="password">Password:</label>
							</td>
							<td valign="top">
								<input type="password"
									id="password" name="password"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="buttons" align="center">
				<span class="button">
					<input type="submit" value="Register" />
				</span>
			</div>
		</g:form>
	</div>
	</body>
	
</html>