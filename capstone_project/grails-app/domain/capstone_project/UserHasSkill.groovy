package capstone_project

import java.util.Date;

class UserHasSkill 
{
	static constraints = 
	{
		user()
		skill()
		//paid()
		dateCreated()
	}
	
	static belongsTo = [user:User, skill:Skill]
	
	//Boolean paid
	Date dateCreated
	
	String toString(){
		skill.s_name
	}
}
