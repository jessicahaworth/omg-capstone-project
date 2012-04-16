package capstone_project
import org.grails.comments.*
import java.util.Date;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;
import org.grails.comments.Commentable;

class User implements Commentable
{
	static constraints = {
		login(blank:false, nullable:false, unique:true)
		password(blank:false, password:true)
		role(inList:["admin", "user"])
		firstName(blank:false, maxSize:50)
		lastName(blank:false, maxSize:50)
		emailAddress(email:true)
		about(maxSize:1000)
	}
	
	//List skills = new ArrayList()
	static hasMany = [	hasSkill:UserSkill,
						memberOf:MemberOfProject]
	
	
	
	String login
	String password
	String role = "user"
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
		return role == "admin"
	}
	
	String toString(){
		login
	}
	
	
}
