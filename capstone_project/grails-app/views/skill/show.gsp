
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
			
				<g:if test="${skillInstance?.skilledUser}">
				<li class="fieldcontain">
					<span id="skilledUser-label" class="property-label"><g:message code="skill.skilledUser.label" default="Skilled User" /></span>
					
						<g:each in="${skillInstance.skilledUser}" var="s">
						<span class="property-value" aria-labelledby="skilledUser-label"><g:link controller="userHasSkill" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
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
