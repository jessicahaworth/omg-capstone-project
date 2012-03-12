<%@ page import="capstone_project.Project" %>



<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="project.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${projectInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="project.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${projectInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'admin', 'error')} ">
	<label for="admin">
		<g:message code="project.admin.label" default="Admin" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${projectInstance?.admin?}" var="a">
    <li><g:link controller="adminOfProject" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="adminOfProject" action="create" params="['project.id': projectInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'adminOfProject.label', default: 'AdminOfProject')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: projectInstance, field: 'member', 'error')} ">
	<label for="member">
		<g:message code="project.member.label" default="Member" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${projectInstance?.member?}" var="m">
    <li><g:link controller="memberOfProject" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="memberOfProject" action="create" params="['project.id': projectInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'memberOfProject.label', default: 'MemberOfProject')])}</g:link>
</li>
</ul>

</div>

