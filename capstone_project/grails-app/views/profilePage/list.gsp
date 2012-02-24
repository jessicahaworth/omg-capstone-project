
<%@ page import="capstone_project.ProfilePage" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'profilePage.label', default: 'ProfilePage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-profilePage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-profilePage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="userName" title="${message(code: 'profilePage.userName.label', default: 'User Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'profilePage.lastName.label', default: 'Last Name')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'profilePage.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="emailAddress" title="${message(code: 'profilePage.emailAddress.label', default: 'Email Address')}" />
					
						<g:sortableColumn property="skillSet" title="${message(code: 'profilePage.skillSet.label', default: 'Skill Set')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'profilePage.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${profilePageInstanceList}" status="i" var="profilePageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${profilePageInstance.userName}">${fieldValue(bean: profilePageInstance, field: "userName")}</g:link></td>
						<%-- the line above sets the id to the userName for each profile --%>
						<td>${fieldValue(bean: profilePageInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: profilePageInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: profilePageInstance, field: "emailAddress")}</td>
					
						<td>${fieldValue(bean: profilePageInstance, field: "skillSet")}</td>
					
						<td><g:formatDate date="${profilePageInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${profilePageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
