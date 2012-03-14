package capstone_project

import java.util.Date;

class ProjectSkill {

	static constraints = 
	{
		project()
		skill()
		//paid()
		dateCreated()
	}
	
	static belongsTo = [project:Project, skill:Skill]
	
	//Boolean paid
	Date dateCreated
	
	String toString(){
		skill.s_name + " is used by " + project.name
	}
}