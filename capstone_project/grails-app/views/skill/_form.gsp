<%@ page import="capstone_project.Skill" %>



<div class="fieldcontain ${hasErrors(bean: skillInstance, field: 's_name', 'error')} required">
	<label for="s_name">
		<g:message code="skill.s_name.label" default="Sname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="s_name" required="" value="${skillInstance?.s_name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: skillInstance, field: 'skilledUser', 'error')} ">
	<label for="skilledUser">
		<g:message code="skill.skilledUser.label" default="Skilled User" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${skillInstance?.skilledUser?}" var="s">
    <li><g:link controller="userHasSkill" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="userHasSkill" action="create" params="['skill.id': skillInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'userHasSkill.label', default: 'UserHasSkill')])}</g:link>
</li>
</ul>

</div>

