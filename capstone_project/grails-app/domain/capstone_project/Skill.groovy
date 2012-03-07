package capstone_project

class SkillSet{
	
	String name
	boolean _deleted
	
	static transients = ['_deleted']
	static belongsTo = [user:User]
	
	def String toString(){
		return name
	}

		static constraints = {
	
	}
}