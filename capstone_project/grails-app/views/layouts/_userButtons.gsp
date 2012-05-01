<div id="userButtons">
	<g:form>
		<fieldset class="buttons">
			<g:link controller="omg" action="editUser" id="${session.user.id}"><div style="border:1px dashed grey;">Edit Info</div></g:link>
			
			<g:link controller="omg" action="createProject"><div style="border:1px dashed grey;">Create Project</div></g:link>
			
			<g:link controller="omg" action="listProjectToUser"><div style="border:1px dashed grey;">Join Projects</div></g:link>
			
			<g:link controller="omg" action="createSkill"><div style="border:1px dashed grey;">Create Skill</div></g:link>
			
			<g:link controller="omg" action="listSkillToUser"><div style="border:1px dashed grey;">Add Skills</div></g:link>
			
			<g:link controller="event"><div style="border:1px dashed grey;">Calendar</div></g:link>
			
			<g:link controller="event" action="create"><div style="border:1px dashed grey;">New Event</div></g:link>
		</fieldset>
	</g:form>
</div>