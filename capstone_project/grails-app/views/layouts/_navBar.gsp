<div id="navBar">
	<g:form>
		<fieldset class="buttons">
			<g:link controller="omg" action="showUser" id="${session.user.id}"><div style="border:1px dashed grey;">My Profile</div></g:link>
			<g:link controller="omg" action="listUser"><div style="border:1px dashed grey;">Browse Users</div></g:link>
			<g:link controller="omg" action="listProjectToUser"><div style="border:1px dashed grey;">Browse Projects</div></g:link>
		</fieldset>
	</g:form>
</div>