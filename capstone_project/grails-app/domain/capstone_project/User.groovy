package capstone_project
import org.grails.comments.*
import java.util.Date;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;
import org.grails.comments.Commentable;

//	this domain represents users of the OMG system.
// 	each user is commentable and thus has messageboard associated with thier homepage
class User implements Commentable
{
	//constraints
	static constraints = {
		login(blank:false, nullable:false, unique:true)		//login must be unique...simple
		password(blank:false, password:true)				//password:true makes sure to encrypt the password and not display it
		role(inList:["admin", "user"])
		firstName(blank:false, maxSize:50)
		lastName(blank:false, maxSize:50)
		emailAddress(email:true)
		about(maxSize:1000)
	}
	
	//mappings - each user may have many skills and be a part of many projects
	static hasMany = [	hasSkill:UserSkill,
						memberOf:MemberOfProject]
	
	
	
	String login			//username
	String password			//pw
	String role = "user"	//role, can be either admin of user.  defaults to user
	String firstName	
	String lastName
	//String phoneNumber
	String emailAddress
	String about = ""
	
	Date dateCreated
	Date lastUpdated
	
	static mapping = {
		//comments sort: "dateCreated", order:"desc"
		skills cascade:"all-delete-orphan"
	}
	
	
	static transients = ['admin']
	boolean isAdmin(){
		//I:	none
		//O:	determines if user is an admin
		//R:	true if user is admin, false otherwise
		return role == "admin"
	}
	
	String toString(){
		login
	}
	
	
}
