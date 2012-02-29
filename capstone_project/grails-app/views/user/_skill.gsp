<div id="skillSet${i}">
    <g:hiddenField name='getSkillList[${i}].id' value='${skillSet.id}'/>
    <g:textField name='getSkillList[${i}].name' value='${skillSet.name}'/>
    <input type="hidden" name='getSkillList[${i}]._deleted' id='getSkillList[${i}]._deleted' value='false'/>
    <span onClick="$('#getSkillList\\[${i}\\]\\._deleted').val('true'); $('#book${i}').hide()"><img src="${resource(dir:'images/skin', file:'database_delete.png')}" /></span>
</div>