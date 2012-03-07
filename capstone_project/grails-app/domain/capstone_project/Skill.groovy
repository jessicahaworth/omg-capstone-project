package capstone_project

class Skill{
	
	String s_name
	boolean deleted
	int index
	
	static transients = ['deleted']
	static belongsTo = [user:User]
	
	def String toString(){
		return "${s_name}"
	}
	
	static mapping={
		index column:"skill_index"
	}

	static constraints = {
		index(blank:false, min:0)
		s_name(blank:false)
	}
}