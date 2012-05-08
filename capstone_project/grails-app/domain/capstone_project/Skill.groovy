package capstone_project


//	this domain represents a skill of interest to OMG.  examples include c++, welding, and/or lathework.
class Skill{
	
	//name of the skill
	String s_name
	boolean deleted  //not sure what this is here for, dont want to delete it not though

	
	static transients = ['deleted']
	//mapping - each skill can be used by many users and many projects
	static hasMany = [userSkill:UserSkill, projectSkill:ProjectSkill]
	
	def String toString(){
		return "${s_name}"
	}
	
	//	not sure what this does, chris worked on this...i dont think it is necessary
	static mapping={
		index column:"skill_index"
	}

	//skill must have a name
	static constraints = {
		//index(blank:false, min:0)
		s_name(blank:false)
	}
}