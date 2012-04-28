
<%@ page import="capstone_project.AdminOfProject" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'adminOfProject.label', default: 'AdminOfProject')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-adminOfProject" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-adminOfProject" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="adminOfProject.user.label" default="User" /></th>
					
						<th><g:message code="adminOfProject.project.label" default="Project" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'adminOfProject.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${adminOfProjectInstanceList}" status="i" var="adminOfProjectInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${adminOfProjectInstance.id}">${fieldValue(bean: adminOfProjectInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: adminOfProjectInstance, field: "project")}</td>
					
						<td><g:formatDate date="${adminOfProjectInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${adminOfProjectInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
