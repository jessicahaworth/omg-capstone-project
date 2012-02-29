<%@ page import="capstone_project.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'login', 'error')} required">
	<label for="login">
		<g:message code="user.login.label" default="Login" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="login" required="" value="${userInstance?.login}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'role', 'error')} ">
	<label for="role">
		<g:message code="user.role.label" default="Role" />
		
	</label>
	<g:select name="role" from="${userInstance.constraints.role.inList}" value="${userInstance?.role}" valueMessagePrefix="user.role" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="50" required="" value="${userInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="50" required="" value="${userInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="user.emailAddress.label" default="Email Address" />
		
	</label>
	<g:field type="email" name="emailAddress" value="${userInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'skills', 'error')} ">
	<label for="skills">
		<g:message code="user.skills.label" default="Skills" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${userInstance?.skills?}" var="s">
    <li><g:link controller="skillSet" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="skillSet" action="create" params="['user.id': userInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'skillSet.label', default: 'SkillSet')])}</g:link>
</li>
</ul>

</div>

