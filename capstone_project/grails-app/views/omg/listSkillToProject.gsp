
<%@ page import="capstone_project.Skill" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'skill.label', default: 'Skill')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="/layouts/navBar" />
		<div id="list-skill" class="content scaffold-list" role="main">
			<h1>Add Skill to Project</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="s_name" title="${message(code: 'skill.s_name.label', default: 'Sname')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${skillInstanceList}" status="i" var="skillInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${skillInstance.id}">${fieldValue(bean: skillInstance, field: "s_name")}</g:link></td>
						
						<td>
							<g:if test = "${!(projectSkills.toString().contains(" "+skillInstance.s_name+" ") || projectSkills.toString().contains(" "+skillInstance.s_name+"]")
								|| projectSkills.toString().contains("["+skillInstance.s_name+" ") || projectSkills.toString().contains("[]"+skillInstance.s_name+"]"))}">
							<g:form>
								<fieldset class="buttons"> 
									<g:link controller="omg" action="addSkillToProject" params = '[skill_id:"${skillInstance.id}", project_id: "${projectInstance.id}"]'><div style="border:1px dashed grey;">Add</div></g:link>
								</fieldset>
							</g:form>
							</g:if>
						</td>
						
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${skillInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
