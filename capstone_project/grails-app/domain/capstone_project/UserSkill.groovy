package capstone_project
import java.util.Date;

//	this domain represents the many to many relationship between users and the skills they have
class UserSkill 
{
	static constraints = 
	{
		user()
		skill()
		//paid()
		dateCreated()
	}
	
	
	//each UserSkill belongs to one user and one skill
	static belongsTo = [user:User, skill:Skill]
	
	Date dateCreated
	
	String toString(){
		user.firstName + " " + user.lastName + " has skill " + skill.s_name
	}
}
