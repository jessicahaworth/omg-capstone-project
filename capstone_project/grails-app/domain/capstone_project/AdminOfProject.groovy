package capstone_project

import java.util.Date;

class AdminOfProject 
{
	static constraints = 
	{
		user()
		project()
		//paid()
		dateCreated()
	}
	
	static belongsTo = [user:User, project:Project]
	
	//Boolean paid
	Date dateCreated
	
	String toString(){
		project.name
	}
}
