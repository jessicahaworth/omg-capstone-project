<script type="text/javascript">
    var childCount = ${userInstance?.skills.size()} + 0;
    
	function addSkill(){
		var clone = $("#skill_clone").clone();
		var htmlId = 'skills['+childCount+'].';
		var skillInput = clone.find("input[id$=s_name]");
		
		clone.find("input[id$=id]")
			.attr('id', htmlId + 'id')
			.attr('name', htmlId + 'id');
		clone.find("input[id$=deleted]")
			.attr('id', htmlId + 'deleted')
			.attr('name', htmlId + 'deleted');
		clone.find("input[id$=new]")
			.attr('id', htmlId + 'new')
			.attr('name', htmlId + 'new')
			.attr('value', 'true');
		skillInput.attr('id', htmlId + 's_name')
			.attr('name', htmlId + 's_name');
		
		clone.attr('id', 'skill'+childCount);
		$("#childList").append(clone);
		clone.show();
		skillInput.focus();
		childCount++;
	}
	
	 $('.del-skill').live('click', function() {
	        //find the parent div
	        var prnt = $(this).parents(".skill-div");
	        //find the deleted hidden input
	        var delInput = prnt.find("input[id$=deleted]");
	        //check if this is still not persisted
	        var newValue = prnt.find("input[id$=new]").attr('value');
	        //if it is new then i can safely remove from dom
	        if(newValue == 'true'){
	            prnt.remove();
	        }else{
	            //set the deletedFlag to true
	            delInput.attr('value','true');
	            //hide the div
	            prnt.hide();
	        }
	    });
</script>

<div id="childList">
    <g:each var="skill" in="${userInstance.skills}" status="i">
        <g:render template='skill' model="['skill':skill,'i':i, 'hidden':false]"/>
    </g:each>
</div>

<input type="button" value="Add Skill" onclick="addSkill();" />