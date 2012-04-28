<%@ page import="org.grails.comments.CommentLink" %>



<div class="fieldcontain ${hasErrors(bean: commentLinkInstance, field: 'commentRef', 'error')} required">
	<label for="commentRef">
		<g:message code="commentLink.commentRef.label" default="Comment Ref" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="commentRef" min="0" required="" value="${fieldValue(bean: commentLinkInstance, field: 'commentRef')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentLinkInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="commentLink.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" required="" value="${commentLinkInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentLinkInstance, field: 'comment', 'error')} required">
	<label for="comment">
		<g:message code="commentLink.comment.label" default="Comment" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="comment" name="comment.id" from="${org.grails.comments.Comment.list()}" optionKey="id" required="" value="${commentLinkInstance?.comment?.id}" class="many-to-one"/>
</div>

