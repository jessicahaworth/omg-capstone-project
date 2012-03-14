<%@ page import="capstone_project.UserSkill" %>



<div class="fieldcontain ${hasErrors(bean: userSkillInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="userSkill.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${capstone_project.User.list()}" optionKey="id" required="" value="${userSkillInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userSkillInstance, field: 'skill', 'error')} required">
	<label for="skill">
		<g:message code="userSkill.skill.label" default="Skill" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="skill" name="skill.id" from="${capstone_project.Skill.list()}" optionKey="id" required="" value="${userSkillInstance?.skill?.id}" class="many-to-one"/>
</div>

