package capstone_project

import java.util.Date;

class User {

    String login
	String password
	String role = "user"
	//ProfilePage profilePage
	static constraints = {
		login(blank:false, nullable:false, unique:true)
		password(blank:false, password:true)
		role(inList:["admin", "user"])
	}
	
	static transients = ['admin']
	boolean isAdmin(){
		return role == "admin"
	}
	
	String toString(){
		login
	}
}
