
<%@ page import="org.grails.comments.CommentLink" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'commentLink.label', default: 'CommentLink')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-commentLink" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-commentLink" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list commentLink">
			
				<g:if test="${commentLinkInstance?.commentRef}">
				<li class="fieldcontain">
					<span id="commentRef-label" class="property-label"><g:message code="commentLink.commentRef.label" default="Comment Ref" /></span>
					
						<span class="property-value" aria-labelledby="commentRef-label"><g:fieldValue bean="${commentLinkInstance}" field="commentRef"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${commentLinkInstance?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="commentLink.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${commentLinkInstance}" field="type"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${commentLinkInstance?.comment}">
				<li class="fieldcontain">
					<span id="comment-label" class="property-label"><g:message code="commentLink.comment.label" default="Comment" /></span>
					
						<span class="property-value" aria-labelledby="comment-label"><g:link controller="comment" action="show" id="${commentLinkInstance?.comment?.id}">${commentLinkInstance?.comment?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${commentLinkInstance?.id}" />
					<g:link class="edit" action="edit" id="${commentLinkInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
