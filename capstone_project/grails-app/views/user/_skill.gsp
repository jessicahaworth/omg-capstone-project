<div id="skill${i}" class="skill-div" <g:if test="${hidden}">style="display:none;"</g:if>>
    <g:hiddenField name='skills[${i}].id' value='${skill?.id}'/>
    <g:hiddenField name='skills[${i}].deleted' value='false'/>
	<g:hiddenField name='skills[${i}].new' value="${skill?.id == null?'true':'false'}"/>
    
    <g:textField name='skills[${i}].s_name' value='${skill?.s_name}' />    

    <span class="del-skill">
        <img src="${resource(dir:'images/skin', file:'database_delete.png')}" 
            style="vertical-align:middle;"/>
    </span>
</div>