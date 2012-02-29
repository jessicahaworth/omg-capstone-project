<script type="text/javascript">
    var childCount = ${authorInstance?.books.size()} + 0;
    
    function addChild() {
        var htmlId = "Skill" + childCount;
        var deleteIcon = "${resource(dir:'images/skin', file:'database_delete.png')}";
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<input type='text' id='getSkillList[" + childCount + "].name' name='getSkillList[" + childCount + "].name' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        var str = “getSkillList["+ childCount +"].name”;
        document.getElementById(str).focus();
        childCount++;
    }
</script>

<div id="childList">
    <g:each var="skillSet" in="${userInstance.skills}" status="i">
        <g:render template='skillSet' model="['skillSet':skillSet,'i':i]"/>
    </g:each>
</div>
<input type="button" value="Add Skill" onclick="addChild();" />
