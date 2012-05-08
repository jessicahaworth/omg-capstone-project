package capstone_project
import org.grails.comments.*
import java.util.Date;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;
import org.grails.comments.Commentable;

//represents a project at OMG
class Project implements Commentable //implements commentable, making each project commentable by users of OMG
{
	//in this case, constraints are listed in the order we would like them to appear when viewing a project page
	static constraints = {
		name()
		description()
		dateCreated()
		lastUpdated()
		
	}
	//name of the project
	String name
	//description of the project
	String description
	
	Date dateCreated
	Date lastUpdated
	//List members = new ArrayList()

	//mappings - each project has many skills and many members
	static hasMany = [projectSkill:ProjectSkill, member:MemberOfProject]
	
	String toString(){
		name
	}
}
