
<%@ page import="capstone_project.SkillSet" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'skillSet.label', default: 'SkillSet')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-skillSet" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-skillSet" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'skillSet.name.label', default: 'Name')}" />
					
						<th><g:message code="skillSet.user.label" default="User" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${skillSetInstanceList}" status="i" var="skillSetInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${skillSetInstance.id}">${fieldValue(bean: skillSetInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: skillSetInstance, field: "user")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${skillSetInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
