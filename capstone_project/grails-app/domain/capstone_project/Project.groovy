package capstone_project
import org.grails.comments.*
import java.util.Date;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;
import org.grails.comments.Commentable;

class Project implements Commentable 
{
	long owner_id
	String name
	
	Date dateCreated
	Date lastUpdated
	
	List Users = new ArrayList()
	static hasMany = [Users:User]
	
    static constraints = {
		String name
    }
}
