<%@ page import="capstone_project.Project" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:render template="/layouts/navBar" />
		<div id="show-project" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list project">
			
				<g:if test="${projectInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="project.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${projectInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="project.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${projectInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="project.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${projectInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="project.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${projectInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectInstance?.member}">
				<li class="fieldcontain">
					<span id="member-label" class="property-label"><g:message code="project.member.label" default="Member" /></span>
					
						<g:each in="${projectInstance.member}" var="m">
						<span class="property-value" aria-labelledby="member-label"><g:link controller="omg" action="showUser" id="${m.user.id}">${m.user.firstName+" "+m.user.lastName}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${projectInstance?.projectSkill}">
				<li class="fieldcontain">
					<span id="projectSkill-label" class="property-label"><g:message code="project.projectSkill.label" default="Project Skill" /></span>
					
						<g:each in="${projectInstance.projectSkill}" var="p">
						<span class="property-value" aria-labelledby="projectSkill-label"><g:link controller="omg" action="showProject" id="${p.project.id}">${p.skill.s_name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
				
			</ol>
			
		</div>
			<!-- TODO  -->
			
			<g:if test="${session.userAdmins.toString().contains(projectInstance.name+",") || session.userAdmins.toString().contains(projectInstance.name+"]")}">
				<g:render template="/layouts/projectButtons" />
			</g:if>
			<!-- TODO  -->
	</body>
</html>