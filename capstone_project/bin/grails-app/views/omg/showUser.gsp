<%@ page import="capstone_project.User" %>
<%@ page import="com.synergyj.grails.plugins.avatar.AvatarTagLib" %>
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
			<h1>
				<g:message code="default.show.label" args="[entityName]" /> 
			</h1>
			<div style="width: 720px;">
				<div style="float: right; width: 100px;">
					<avatar:gravatar email="${userInstance.emailAddress}"  alt="lol" size="80" defaultGravatarUrl="${createLinkTo(absolute: true, dir:'/images',file:'default_avatar.png')}" />
				</div>
			</div>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
			
				<g:if test="${userInstance?.login}">
				<li class="fieldcontain">
					<span id="login-label" class="property-label"><g:message code="user.login.label" default="Login" /></span>
					
						<span class="property-value" aria-labelledby="login-label"><g:fieldValue bean="${userInstance}" field="login"/>
						(<g:fieldValue bean="${userInstance}" field="role"/>)
						</span>
					
				</li>
				</g:if>
			
				<g:if test="${userInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="user.firstName.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${userInstance}" field="firstName"/>
					    <g:fieldValue bean="${userInstance}" field="lastName"/>
						</span>
					
				</li>
				</g:if>
			
			
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
				
				
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="user.about.label" default="About" /></span>
					<g:if test="${userInstance?.about}">
						<span class="property-value" aria-labelledby="about-label"><g:fieldValue bean="${userInstance}" field="about"/>
						</span>
						</g:if>
				</li>
				
				
				
				<li class="fieldcontain">
					<span id="hasSkill-label" class="property-label"><g:message code="user.hasSkill.label" default="Skills" /></span>
					<g:if test="${userInstance?.hasSkill}">
						<g:each in="${userInstance.hasSkill}" var="h">
						<span class="property-value" aria-labelledby="hasSkill-label"><g:link controller="omg" action="showSkill" id="${h.skill.id}">${h.skill.s_name}</g:link></span>
						</g:each>
						</g:if>
						<g:else><span>&nbsp&nbsp&nbspnone</span>  </g:else>
					
				</li>
				
			
				
				<li class="fieldcontain">
					<span id="memberOf-label" class="property-label"><g:message code="user.memberOf.label" default="Memberships" /></span>
					<g:if test="${userInstance?.memberOf}">
						<g:each in="${userInstance.memberOf}" var="m">
						<span class="property-value" aria-labelledby="memberOf-label"><g:link controller="omg" action="showProject" id="${m.project.id}">${m.project.name}</g:link>
						<g:if test = "${session.userAdmins.toString().contains(m.project.name+",") || session.userAdmins.toString().contains(m.project.name+"]")}">
						admin
						</g:if>
						</span>
						</g:each>
						</g:if>
						<g:else><span>&nbsp&nbsp&nbspnone</span>  </g:else>
				</li>
				
			
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
