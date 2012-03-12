<%@ page import="capstone_project.MemberOfProject" %>



<div class="fieldcontain ${hasErrors(bean: memberOfProjectInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="memberOfProject.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${capstone_project.User.list()}" optionKey="id" required="" value="${memberOfProjectInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: memberOfProjectInstance, field: 'project', 'error')} required">
	<label for="project">
		<g:message code="memberOfProject.project.label" default="Project" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="project" name="project.id" from="${capstone_project.Project.list()}" optionKey="id" required="" value="${memberOfProjectInstance?.project?.id}" class="many-to-one"/>
</div>

