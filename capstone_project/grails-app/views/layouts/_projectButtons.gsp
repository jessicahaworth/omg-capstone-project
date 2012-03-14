<div id="projectButtons">
	<g:form>
		<fieldset class="buttons">
			
			<g:link controller="omg" action="listUserToProject" params ='[project_id:"${projectInstance.id}"]'>
				<div style="border:1px dashed grey;">
					Edit Project
				</div>
			</g:link>
			
			<g:link controller="omg" action="listUserToProject" params ='[project_id:"${projectInstance.id}"]'>
				<div style="border:1px dashed grey;">
					Add Members
				</div>
			</g:link>
			
			<g:link controller="omg" action="listSkillToProject" params ='[project_id:"${projectInstance.id}"]'>
				<div style="border:1px dashed grey;">
					Add Skills
				</div>
			</g:link>
			
		</fieldset>
	</g:form>
</div>