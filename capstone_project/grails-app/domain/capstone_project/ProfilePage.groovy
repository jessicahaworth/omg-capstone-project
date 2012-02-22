package capstone_project

class ProfilePage implements Serializable {

	static constraints = {
		lastName(blank:false, maxSize:50)
		firstName(blank:false, maxSize:50)
		emailAddress(email:true)
		//phoneNumber(matches:" [0-9] +")
		skillSet()
		}
	String firstName
	String lastName
	//String phoneNumber
	Date dateCreated
	Date lastUpdated
	String skillSet
	String emailAddress
	
	static mapping = {
		id generator:'assigned', column:'lastName', name:'lastName'
		}
}
