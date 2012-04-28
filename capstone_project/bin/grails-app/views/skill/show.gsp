
<%@ page import="capstone_project.Skill" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'skill.label', default: 'Skill')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-skill" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
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
						<span class="property-value" aria-labelledby="projectSkill-label"><g:link controller="projectSkill" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${skillInstance?.userSkill}">
				<li class="fieldcontain">
					<span id="userSkill-label" class="property-label"><g:message code="skill.userSkill.label" default="User Skill" /></span>
					
						<g:each in="${skillInstance.userSkill}" var="u">
						<span class="property-value" aria-labelledby="userSkill-label"><g:link controller="userSkill" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${skillInstance?.id}" />
					<g:link class="edit" action="edit" id="${skillInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
