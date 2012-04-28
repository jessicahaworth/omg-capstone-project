<%@ page import="org.grails.comments.Comment" %>



<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'body', 'error')} required">
	<label for="body">
		<g:message code="comment.body.label" default="Body" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="body" required="" value="${commentInstance?.body}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'posterClass', 'error')} required">
	<label for="posterClass">
		<g:message code="comment.posterClass.label" default="Poster Class" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="posterClass" required="" value="${commentInstance?.posterClass}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: commentInstance, field: 'posterId', 'error')} required">
	<label for="posterId">
		<g:message code="comment.posterId.label" default="Poster Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="posterId" min="0" required="" value="${fieldValue(bean: commentInstance, field: 'posterId')}"/>
</div>

