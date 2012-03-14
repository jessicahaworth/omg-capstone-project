package capstone_project


import java.util.Date;

class UserSkill 
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
		user.firstName + " " + user.lastName + " has skill " + skill.s_name
	}
}
