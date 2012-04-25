<g:set var="comments" value="${commentable.comments}"></g:set>
<div id="comments" class="commentable">
	<g:render template="/commentable/comment" 
			  collection="${comments.sort{it.dateCreated}.reverse()}" 
			  var="comment" 
			  plugin="commentable" 
			  model="[noEscape:noEscape]" />
</div>
<div id="addComment" class="addComment">
	<h2 class="addCommentTitle">
		<a href="#commentEditor" onclick="document.getElementById('addCommentContainer').style.display='';">
			<g:message code="comment.add.title" default="Post to Wall"></g:message>
		</a>
	</h2>
	<div id="addCommentContainer" class="addCommentContainer" style="display:none;">
		<div class="addCommentDescription">
			<g:message code="comment.add.description" default=""></g:message>
		</div>
		<a name="commentEditor"></a>
		<g:formRemote name="addCommentForm" url="[controller:'commentable',action:'add']" update="comments">
			<plugin:isAvailable name="grails-ui">
				<gui:richEditor id='commentBody' name="comment.body" value='' width="100%" />
			</plugin:isAvailable>
			<plugin:isNotAvailable name="grails-ui">
				<g:textArea id="commentBody" name="comment.body" /> <br />
			</plugin:isNotAvailable>
			<g:hiddenField name="update" value="comments" />			
			<g:hiddenField name="commentLink.commentRef" value="${commentable.id}" />
			<g:hiddenField name="commentLink.type" value="${commentable.class.name}" />			
			<g:hiddenField name="commentPageURI" value="${request.forwardURI}"></g:hiddenField>
			<g:submitButton name="${g.message(code:'comment.post.button.name', 
											 'default':'Post')}"></g:submitButton>
		</g:formRemote>
	</div>
</div>