<%@ page import="capstone_project.SkillSet" %>



<div class="fieldcontain ${hasErrors(bean: skillSetInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="skillSet.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${skillSetInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: skillSetInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="skillSet.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${capstone_project.User.list()}" optionKey="id" required="" value="${skillSetInstance?.user?.id}" class="many-to-one"/>
</div>

