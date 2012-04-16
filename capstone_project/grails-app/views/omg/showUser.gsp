<%@ page import="capstone_project.User" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="/layouts/navBar" />
		<div id="show-user" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<g:if test="${userInstance?.login}">
				<li class="fieldcontain">
					<span id="login-label" class="property-label"><g:message code="user.login.label" default="Login" /></span>
					
						<span class="property-value" aria-labelledby="login-label"><g:fieldValue bean="${userInstance}" field="login"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.role}">
				<li class="fieldcontain">
					<span id="role-label" class="property-label"><g:message code="user.role.label" default="Role" /></span>
					
						<span class="property-value" aria-labelledby="role-label"><g:fieldValue bean="${userInstance}" field="role"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="user.firstName.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${userInstance}" field="firstName"/>
						
<%--						<g:message code="user.lastName.label" default="Last Name" />--%>
					    <g:fieldValue bean="${userInstance}" field="lastName"/>
						</span>
					
				</li>
				</g:if>
			
<%--				<g:if test="${userInstance?.lastName}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="lastName-label" class="property-label"><g:message code="user.lastName.label" default="Last Name" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${userInstance}" field="lastName"/></span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
				<g:if test="${userInstance?.emailAddress}">
				<li class="fieldcontain">
					<span id="emailAddress-label" class="property-label"><g:message code="user.emailAddress.label" default="Email Address" /></span>
					
						<span class="property-value" aria-labelledby="emailAddress-label"><g:fieldValue bean="${userInstance}" field="emailAddress"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="user.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${userInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="user.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${userInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
				
				<g:if test="${userInstance?.hasSkill}">
				<li class="fieldcontain">
					<span id="hasSkill-label" class="property-label"><g:message code="user.hasSkill.label" default="Has Skill" /></span>
					
						<g:each in="${userInstance.hasSkill}" var="h">
						<span class="property-value" aria-labelledby="hasSkill-label"><g:link controller="omg" action="showSkill" id="${h.skill.id}">${h.skill.s_name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.memberOf}">
				<li class="fieldcontain">
					<span id="memberOf-label" class="property-label"><g:message code="user.memberOf.label" default="Member Of" /></span>
					
						<g:each in="${userInstance.memberOf}" var="m">
						<span class="property-value" aria-labelledby="memberOf-label"><g:link controller="omg" action="showProject" id="${m.project.id}">${m.project.name}</g:link>
						<g:if test = "${session.userAdmins.toString().contains(m.project.name+",") || session.userAdmins.toString().contains(m.project.name+"]")}">
						admin
						</g:if>
						</span>
						</g:each>
				</li>
				</g:if>
			
			</ol>
		</div>
		<g:if test="${session.user.id == userInstance.id}">
			<g:render template="/layouts/userButtons" />
		</g:if>
		<div>
			<comments:render bean="${userInstance}" />
		</div>
	</body>
</html>
