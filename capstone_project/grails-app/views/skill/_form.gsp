<%@ page import="capstone_project.Skill" %>



<div class="fieldcontain ${hasErrors(bean: skillInstance, field: 's_name', 'error')} required">
	<label for="s_name">
		<g:message code="skill.s_name.label" default="Sname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="s_name" required="" value="${skillInstance?.s_name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: skillInstance, field: 'projectSkill', 'error')} ">
	<label for="projectSkill">
		<g:message code="skill.projectSkill.label" default="Project Skill" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${skillInstance?.projectSkill?}" var="p">
    <li><g:link controller="projectSkill" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="projectSkill" action="create" params="['skill.id': skillInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'projectSkill.label', default: 'ProjectSkill')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: skillInstance, field: 'userSkill', 'error')} ">
	<label for="userSkill">
		<g:message code="skill.userSkill.label" default="User Skill" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${skillInstance?.userSkill?}" var="u">
    <li><g:link controller="userSkill" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="userSkill" action="create" params="['skill.id': skillInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'userSkill.label', default: 'UserSkill')])}</g:link>
</li>
</ul>

</div>

