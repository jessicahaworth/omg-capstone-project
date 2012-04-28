<div style="border:1px solid blue;">
<div id="comment${comment.id}" class="comment">
	<!-- 
	<div class='permalink'>
		<a href="#comment_${comment.id}" name="comment_${comment.id}">
			<g:message code="comment.link.text" default="link"></g:message>
		</a>
	</div>
	 -->
	<plugin:isAvailable name="avatar">
		<div class="avatar">	
			<avatar:gravatar cssClass="commentAvatar" size="50"
			        email="${comment?.poster.emailAddress}" gravatarRating="R"
			        defaultGravatarUrl="${createLinkTo(absolute: true, dir:'/images',file:'grails-icon.png')}"/>
		</div>			
	</plugin:isAvailable>
	

		<div class='commentBody'>
			<g:if test="${noEscape}">
				${comment?.body}
			</g:if>
			<g:else>
	        	${comment?.body?.encodeAsHTML()}				
			</g:else>
		</div>

	<div style="border:1px dashed blue;text-align:right;background-color:AliceBlue;">
		<div class="commentDetails">
			<g:formatDate format="MMM dd, yyyy HH:mm a" date="${comment.dateCreated}"/>
			<g:link controller="User" action="show" id="${comment?.posterId}">${comment?.poster}</g:link>	
		</div>
	</div>
</div>
</div>