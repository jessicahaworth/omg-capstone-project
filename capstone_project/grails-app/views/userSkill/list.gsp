
<%@ page import="capstone_project.UserSkill" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'userSkill.label', default: 'UserSkill')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-userSkill" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-userSkill" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="userSkill.user.label" default="User" /></th>
					
						<th><g:message code="userSkill.skill.label" default="Skill" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'userSkill.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${userSkillInstanceList}" status="i" var="userSkillInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${userSkillInstance.id}">${fieldValue(bean: userSkillInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: userSkillInstance, field: "skill")}</td>
					
						<td><g:formatDate date="${userSkillInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${userSkillInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
