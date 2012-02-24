<%@ page import="capstone_project.ProfilePage" %>



<div class="fieldcontain ${hasErrors(bean: profilePageInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="profilePage.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${profilePageInstance?.userName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profilePageInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="profilePage.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" maxlength="50" required="" value="${profilePageInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profilePageInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="profilePage.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" maxlength="50" required="" value="${profilePageInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profilePageInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="profilePage.emailAddress.label" default="Email Address" />
		
	</label>
	<g:field type="email" name="emailAddress" value="${profilePageInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: profilePageInstance, field: 'skillSet', 'error')} ">
	<label for="skillSet">
		<g:message code="profilePage.skillSet.label" default="Skill Set" />
		
	</label>
	<g:textField name="skillSet" value="${profilePageInstance?.skillSet}"/>
</div>

