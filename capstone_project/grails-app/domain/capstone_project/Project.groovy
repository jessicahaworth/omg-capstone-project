package capstone_project
import org.grails.comments.*
import java.util.Date;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;
import org.grails.comments.Commentable;

class Project implements Commentable 
{
	static constraints = {
		name()
		description()
		dateCreated()
		lastUpdated()
		
	}
	String name
	String description
	
	Date dateCreated
	Date lastUpdated
	//List members = new ArrayList()

	

	static hasMany = [admin:AdminOfProject, member:MemberOfProject]
	
	String toString(){
		name
	}
}
