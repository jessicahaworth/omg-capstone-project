package capstone_project

import java.util.Date;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;

class User {

	String login
	String password
	String role = "user"
	String firstName
	String lastName
	//String phoneNumber
	String emailAddress
	List skills = new ArrayList()
	static hasMany = [skills:SkillSet]
	
	Date dateCreated
	Date lastUpdated
	
	static mapping = {
		skills cascade:"all-delete-orphan"
	}
	
	def getSkillList(){
		return LazyList.decorate(skills, FactoryUtils.instantiateFactor(SkillSet.class))
	}
	
	
	//ProfilePage profilePage
	static constraints = {
		login(blank:false, nullable:false, unique:true)
		password(blank:false, password:true)
		role(inList:["admin", "user"])
		firstName(blank:false, maxSize:50)
		lastName(blank:false, maxSize:50)
		emailAddress(email:true)
		//skillSet(blank: true, nullable: true, inList:["Java","C++", "HTML", "PERL", "Prolog", "C", "Javascript", "Python", "Visual Basic", "LUA", "Cobalt", "Grails", "Fortran", "Rails", "Assembly", "" ])
		
		//phoneNumber(matches:" [0-9] +")
	}
	
	static transients = ['admin']
	boolean isAdmin(){
		return role == "admin"
	}
	
	String toString(){
		login
	}
	
	
}
