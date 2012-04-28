<%@ page import="capstone_project.UserHasSkill" %>



<div class="fieldcontain ${hasErrors(bean: userHasSkillInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="userHasSkill.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${capstone_project.User.list()}" optionKey="id" required="" value="${userHasSkillInstance?.user?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userHasSkillInstance, field: 'skill', 'error')} required">
	<label for="skill">
		<g:message code="userHasSkill.skill.label" default="Skill" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="skill" name="skill.id" from="${capstone_project.Skill.list()}" optionKey="id" required="" value="${userHasSkillInstance?.skill?.id}" class="many-to-one"/>
</div>

