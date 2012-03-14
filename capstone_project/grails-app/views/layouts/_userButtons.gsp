<div id="userButtons">
	<g:form>
		<fieldset class="buttons">
			<g:link controller="omg" action="editUser" id="${session.user.id}"><div style="border:1px dashed grey;">Edit Info</div></g:link>
			<g:link controller="omg" action="listProjectToUser"><div style="border:1px dashed grey;">Join Projects</div></g:link>
			<g:link controller="omg" action="listSkillToUser"><div style="border:1px dashed grey;">Add Skills</div></g:link>
		</fieldset>
	</g:form>
</div>