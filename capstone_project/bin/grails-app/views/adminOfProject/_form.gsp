<%@ page import="capstone_project.AdminOfProject" %>



<div class="fieldcontain ${hasErrors(bean: adminOfProjectInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="adminOfProject.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${capstone_project.User.list()}" optionKey="id" required="" value="${adminOfProjectInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: adminOfProjectInstance, field: 'project', 'error')} required">
	<label for="project">
		<g:message code="adminOfProject.project.label" default="Project" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="project" name="project.id" from="${capstone_project.Project.list()}" optionKey="id" required="" value="${adminOfProjectInstance?.project?.id}" class="many-to-one"/>
</div>

