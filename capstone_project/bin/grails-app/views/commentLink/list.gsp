
<%@ page import="org.grails.comments.CommentLink" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'commentLink.label', default: 'CommentLink')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-commentLink" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-commentLink" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="commentRef" title="${message(code: 'commentLink.commentRef.label', default: 'Comment Ref')}" />
					
						<g:sortableColumn property="type" title="${message(code: 'commentLink.type.label', default: 'Type')}" />
					
						<th><g:message code="commentLink.comment.label" default="Comment" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${commentLinkInstanceList}" status="i" var="commentLinkInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${commentLinkInstance.id}">${fieldValue(bean: commentLinkInstance, field: "commentRef")}</g:link></td>
					
						<td>${fieldValue(bean: commentLinkInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: commentLinkInstance, field: "comment")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${commentLinkInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
