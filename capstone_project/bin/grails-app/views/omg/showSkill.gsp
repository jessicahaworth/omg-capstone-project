
<%@ page import="capstone_project.Skill" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'skill.label', default: 'Skill')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="/layouts/navBar" />
		<div id="show-skill" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list skill">
			
				<g:if test="${skillInstance?.s_name}">
				<li class="fieldcontain">
					<span id="s_name-label" class="property-label"><g:message code="skill.s_name.label" default="Sname" /></span>
					
						<span class="property-value" aria-labelledby="s_name-label"><g:fieldValue bean="${skillInstance}" field="s_name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${skillInstance?.projectSkill}">
				<li class="fieldcontain">
					<span id="projectSkill-label" class="property-label"><g:message code="skill.projectSkill.label" default="Project Skill" /></span>
					
						<g:each in="${skillInstance.projectSkill}" var="p">
						<span class="property-value" aria-labelledby="projectSkill-label"><g:link action="showProject" id="${p.project.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${skillInstance?.userSkill}">
				<li class="fieldcontain">
					<span id="userSkill-label" class="property-label"><g:message code="skill.userSkill.label" default="User Skill" /></span>
					
						<g:each in="${skillInstance.userSkill}" var="u">
						<span class="property-value" aria-labelledby="userSkill-label"><g:link action="showUser" id="${u.user.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			
		</div>
	</body>
</html>
