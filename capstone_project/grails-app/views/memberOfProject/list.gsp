
<%@ page import="capstone_project.MemberOfProject" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'memberOfProject.label', default: 'MemberOfProject')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-memberOfProject" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-memberOfProject" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="memberOfProject.user.label" default="User" /></th>
					
						<th><g:message code="memberOfProject.project.label" default="Project" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'memberOfProject.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${memberOfProjectInstanceList}" status="i" var="memberOfProjectInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${memberOfProjectInstance.id}">${fieldValue(bean: memberOfProjectInstance, field: "user")}</g:link></td>
					
						<td>${fieldValue(bean: memberOfProjectInstance, field: "project")}</td>
					
						<td><g:formatDate date="${memberOfProjectInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${memberOfProjectInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
