package capstone_project

import java.util.Date;

//	used to represent the many to many relationship between projects and skills
class ProjectSkill {

	static constraints = 
	{
		project()
		skill()
		//paid()
		dateCreated()
	}
	
	//mapping - each ProjectSkill object belongs to one project and one skill
	static belongsTo = [project:Project, skill:Skill]
	
	//Boolean paid
	Date dateCreated
	
	String toString(){
		skill.s_name + " is used by " + project.name
	}
}