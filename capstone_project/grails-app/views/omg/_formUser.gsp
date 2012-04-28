<%@ page import="capstone_project.User"%>

<div
	class="fieldcontain ${hasErrors(bean: userInstance, field: 'login', 'error')} required">
	<label for="login"> <g:message code="user.login.label"
			default="Login" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="login" required="" value="${userInstance?.login}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password"> <g:message code="user.password.label"
			default="Password" /> <span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required=""
		value="${userInstance?.password}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
	<label for="firstName"> <g:message code="user.firstName.label"
			default="First Name" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="50" required=""
		value="${userInstance?.firstName}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
	<label for="lastName"> <g:message code="user.lastName.label"
			default="Last Name" /> <span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="50" required=""
		value="${userInstance?.lastName}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: userInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress"> <g:message
			code="user.emailAddress.label" default="Email Address" />

	</label>
	<g:field type="email" name="emailAddress"
		value="${userInstance?.emailAddress}" />
</div>

<div
	class="fieldcontain ${hasErrors(bean: userInstance, field: 'about', 'error')} ">
	<label for="about"> <g:message
			code="user.about.label" default="About" />
	</label>
	<g:field type="text" name="about" size="55"
		value="${userInstance?.about}" />
</div>
