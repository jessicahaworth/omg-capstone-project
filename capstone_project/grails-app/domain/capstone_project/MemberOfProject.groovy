package capstone_project

import java.util.Date;

//	this domain is used to capture the many to many relationship existing between users and projects
//  each user may be part of many projects and each project may have many users
class MemberOfProject 
{
	//	each MemberOfProject instance has a user and a project associated with it
	static constraints = 
	{
		user()
		project()
		//paid()
		dateCreated()	//time the user became a member of the project
	}
	
	//mapping - each memberofproject object belongs to one user and one project
	static belongsTo = [user:User, project:Project]
	
	//	true if the member is admin of the project
	Boolean admin
	Date dateCreated //time the user became a member of the project
	
	// toString method, returns a user object as a string in a reasonable form
	String toString(){
		user.firstName + " " + user.lastName + " is a member of " + project.name
	}
}