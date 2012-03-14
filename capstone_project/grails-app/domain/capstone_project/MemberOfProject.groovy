package capstone_project

import java.util.Date;

class MemberOfProject 
{
	static constraints = 
	{
		user()
		project()
		//paid()
		dateCreated()
	}
	
	static belongsTo = [user:User, project:Project]
	
	Boolean admin
	Date dateCreated
	
	String toString(){
		user.firstName + " " + user.lastName + " is a member of " + project.name
	}
}