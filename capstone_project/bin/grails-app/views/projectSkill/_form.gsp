<%@ page import="capstone_project.ProjectSkill" %>



<div class="fieldcontain ${hasErrors(bean: projectSkillInstance, field: 'project', 'error')} required">
	<label for="project">
		<g:message code="projectSkill.project.label" default="Project" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="project" name="project.id" from="${capstone_project.Project.list()}" optionKey="id" required="" value="${projectSkillInstance?.project?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: projectSkillInstance, field: 'skill', 'error')} required">
	<label for="skill">
		<g:message code="projectSkill.skill.label" default="Skill" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="skill" name="skill.id" from="${capstone_project.Skill.list()}" optionKey="id" required="" value="${projectSkillInstance?.skill?.id}" class="many-to-one"/>
</div>

